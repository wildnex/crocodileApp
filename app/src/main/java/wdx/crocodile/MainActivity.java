package wdx.crocodile;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private final String mEasy[] = {"Банан", "Мед", "Клоун", "Лягушка", "Вентилятор", "Дитя сатаны", "Депутат", "Пикап", "Дятел", "Самогон", "Дельфин", "Забить", "Коробка передач", "Торнадо", "Нюхательный табак", "Яйцо динозавра", "Горбатый зэк", "Зеленый слоник", "Тертый калач", "Самовозгорание", "Соска", "Балет", "Обруч", "Узел", "Дать на лапу", "Сокровище трудовика", "Север помнит", "Подложить свинью", "Беговая дорожка", "Арбуз", "Качели", "Лыжня", "Мороженное", "Физкультура", "Клизма", "Джеки Чан", "Пылесос", "Дворецкий", "Гусеница", "Джойстик", "Твистер"};
    private final String mNormal[] = {"Сломленный духом", "Дельфин", "Подводная лодка", "Креветка", "Божья коровка", "Кокаиновый утренник", "Порнография", "Следопыт", "Волосатый примат", "Туалетный утёнок", "Слизняк", "Пнутая кошка", "Шаром покати", "Щедрый еврей", "Трубочист", "Нарцисс", "Конец", "Веселая молочница", "Стильный гопник", "Хвостатый зверь", "Падальщик", "Тираннозавр", "Качан", "Дискета", "Кавказская пленница", "Скунс холостяк", "Игра крокодил", "Мужские стринги", "Леденец", "Саждение на кол", "Киви", "Страшный запор", "Всадник без головы", "Тверк ботаника", "Полный привод", "Пейджер", "Инопланетянин", "Алхимик", "Параша", "Высунуть хобот", "Дешёвые понты", "Кочки", "Туман", "Воробей шизофреник", "Подработка ершиком", "Бревно", "Бомж", "Улитка", "Привет", "Запретный плод", "Котопес", "Пенопласт", "Молекула", "Хипстер", "Консьержка"};
    private final String mHard[] = {"Голубой песец", "Теща джигурды", "Галлюцинации девственницы", "Белая горячка", "Розовый тазик", "Уролог подхалим", "Аниме", "Паз", "Шизофрения", "Мурчать", "Стоять", "Жалобный писк", "Отпиленная благодать", "Чокнутый", "Самосвал", "Лишняя кнопка", "Раскрепощенный мопс", "Пузо карапуза", "Жижа", "Бутылка абсента", "Москвич", "Салки", "Пропаганда", "Сомелье фрилансер", "Симулировать", "Половая трещина", "Невесомость", "Родильное отделение", "Молчанка", "Самодельный сын", "Тварь", "Намыленный негр", "Букашка", "Передернуть затвор", "Розыгрыш", "Жук навозник", "Подлиза", "Портовая шлюпка", "Достать закладку", "Логика", "Сексуальная революция", "Добротная канализация", "Толерантность", "Массоны", "Смайлик", "Домогательство", "Реальность", "Бабник", "Человек", "Звезда", "Бешеная пчела", "Хоббит", "Колобок", "Паркур", "Баклажан"};
    private final String mInsane[] = {"Уединение с прищепкой", "Оценить по достоинству", "Эффектная блондинка", "Гламурный падонок", "Фетишист", "Личные пожитки", "Отрицание", "Трезвый кукушенок", "Издевательство", "Червяк задира", "Бесконечное ничто", "Мерзкий чудак", "Каракатица", "Смешарик", "Завтра", "Изображать", "Мастер над шептунами", "Проктолог затейник", "Богомол", "Член партии", "Горький хрен", "Приход прихожан", "Вялый глист", "Мученик", "Ночной горшок", "Карательная порка", "Доходяга за пивом", "Разумное одиночество", "Мокрый буйок", "Милая харя", "Уздечка конюха", "Пионер в засаде"};
    private final String mHYIP[] = {"Навальный", "Собянин", "Гироскутер", "Телеграм", "Путин", "Серия пенальти", "Хайп", "Крымский мост", "Это фиаско братан", "Дружко", "Ксения Собчак", "Ибица", "Трамп", "Цвет настроения синий", "Золотов", "Илон Маск", "Пенсионная реформа", "Майдан", "Дед мороз", "Версус", "Повестка", "Митинг", "Электросамокат", "Стендап"};
    private final int total = mEasy.length + mNormal.length + mHard.length + mInsane.length + mHYIP.length; //Колличество слов
    private final String mUsedSave = "SAVE_USED", exNumSave = "SAVE_NUM"; //Для сохранения в SharedPreferences
    private SoundPool mySound;
    private int soundClick, soundDisturbance; //Для звуков кнопки
    private final Queue<String> finalArray = new LinkedList<>(); //Очередь слов
    private DrawerLayout drawer; //Для обработки навбара
    private long pressedAgain; //Для сохранения времени нажатия
    private TextView textView, textView2; //Главный текст
    private CheckBox checkEasy, checkNormal, checkHard, checkInsane, checkHYIP, checkPaging; //Чекбоксы в навбаре
    private int exNum = 0; //Счетчик для mUsed
    private SharedPreferences sPref; //Для сохранения использованных
    private String[] mUsed = new String[total]; //Использованные слова
    private String currentWord = ""; //Настоящее слово

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sPref = getPreferences(MODE_PRIVATE);
        setContentView(R.layout.activity_main); //Ищем Viev элементы
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        checkEasy = findViewById(R.id.checkEasy);
        checkNormal = findViewById(R.id.checkNormal);
        checkHard = findViewById(R.id.checkHard);
        checkInsane = findViewById(R.id.checkInsane);
        checkHYIP = findViewById(R.id.checkHYIP);
        checkPaging = findViewById(R.id.checkPaging);
        drawer = findViewById(R.id.drawer_layout);
        SetTypeFace();
        mySound = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
        soundClick = mySound.load(this, R.raw.btnm, 1);
        soundDisturbance = mySound.load(this, R.raw.btnm2, 1); //Находим звук
        Arrays.fill(mUsed, "0");  //Заполняем массив использованных слов значениями
        loadText();  //Загружаем массив использованных слов из SharedPreferences
        ListBuilder(); //Составляем очередь в зависимости от чекбоксов

    }

    public void newWord(View view) {
        textView2.setVisibility(View.GONE); //Скрываем текст правил
        if (checkPaging.isChecked() && (pressedAgain + 5000 > System.currentTimeMillis())) { //Если чекбокс "запретить перелистывания" включен и с времени приведущего нажатия прошло менее 5 секунд.
            textView.setText(getString(R.string.trying_to_list, currentWord));
            mySound.play(soundDisturbance, 1, 1, 1, 0, 1);
        } else {
            while (!finalArray.isEmpty() && isAlreadyUsed(finalArray.peek())) {
                finalArray.poll();
            }
            currentWord = finalArray.poll();
            if (currentWord == null) {
                // Очередь пуста сообщаем об этом и открываем навбар
                Toast.makeText(this, R.string.end_of_array, Toast.LENGTH_SHORT).show();
                drawer.openDrawer(Gravity.START);
            } else {
                textView.setText(currentWord); //Выводим не использованное ранее слово
                mUsed[exNum++] = currentWord;  //Записываем его в использованные
            }
        }
        mySound.play(soundClick, 1, 1, 1, 0, 1); //Звук нажатия кнопки
        pressedAgain = System.currentTimeMillis(); //Сохраняем время нажатия

    }

    private boolean isAlreadyUsed(String word) {
        for (String i : mUsed) {     //Перебираем элементы массива использованных слов
            if (i.equals(word)) {    //Пока не найдем совпадения с настоящим словом
                return true;
            }
        }
        return false;
    }

    public void navBar(View view) {   //Метод для кнопки открытия навбара (стрелка)
        drawer.openDrawer(Gravity.START);
    }

    public void onCheckboxClicked(View view) {
        ListBuilder();
    }

    private void ListBuilder() {
        finalArray.clear();
        List<String> temp = new ArrayList<>();
        if (checkEasy.isChecked()) temp.addAll(Arrays.asList(mEasy));
        if (checkNormal.isChecked()) temp.addAll(Arrays.asList(mNormal));
        if (checkHard.isChecked()) temp.addAll(Arrays.asList(mHard));
        if (checkInsane.isChecked()) temp.addAll(Arrays.asList(mInsane));
        if (checkHYIP.isChecked()) temp.addAll(Arrays.asList(mHYIP));
        Collections.shuffle(temp);
        finalArray.addAll(temp);
        if (finalArray.isEmpty())
            Toast.makeText(this, R.string.nothing_checked, Toast.LENGTH_SHORT).show();  //Намекаем что так не пойдет
    }

    private void saveText() {   //Сохраняем в Preferences
        StringBuilder save = new StringBuilder();   //В виде строки
        for (String i : mUsed) {
            save.append(i).append(",");  //С разделителем ","
        }
        sPref.edit()
                .putString(mUsedSave, save.toString())
                .putInt(exNumSave, exNum)  //И переменную счетчика exNum
                .apply();

    }

    private void loadText() {      //Загружаем из Preferences
        String savedText = sPref.getString(mUsedSave, "");
        StringTokenizer st = new StringTokenizer(savedText, ","); //Разделяем обратно по токену
        for (int i = 0; i < total && st.hasMoreTokens(); i++) {
                mUsed[i] = st.nextToken();
        }

        exNum = sPref.getInt(exNumSave, 0);  //Переменную просто грузим
    }

    @Override
    protected void onDestroy() {    //Сохраняем при закрытии
        super.onDestroy();
        saveText();
    }

    public void refresh(View view) {  //Метод для кнопки "работы с нуля"
        Arrays.fill(mUsed, "0");  //Массив использованных обнуляем
        exNum = 0;    //Счетчики обнуляем
        ListBuilder(); //Строим очередь заново
        Toast.makeText(this, R.string.refreshed, Toast.LENGTH_SHORT).show();  //Уведомляем
    }

    private void SetTypeFace() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/ArbatDi.ttf"); //Ставим шрифт
        textView.setTypeface(tf);
        textView2.setTypeface(tf);
        checkEasy.setTypeface(tf);
        checkNormal.setTypeface(tf);
        checkHard.setTypeface(tf);
        checkInsane.setTypeface(tf);
        checkHYIP.setTypeface(tf);
        checkPaging.setTypeface(tf);
    }


}

