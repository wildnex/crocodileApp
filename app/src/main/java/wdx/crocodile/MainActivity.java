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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private final String mEasy[] = {"Банан", "Мед", "Клоун", "Лягушка", "Вентилятор", "Дитя сатаны", "Депутат", "Пикап", "Дятел", "Самогон", "Дельфин", "Забить", "Коробка передач", "Торнадо", "Нюхательный табак", "Яйцо динозавра", "Горбатый зэк", "Зеленый слоник", "Тертый калач", "Самовозгорание", "Соска", "Балет", "Обруч", "Узел",
            "Дать на лапу", "Сокровище трудовика", "Север помнит", "Подложить свинью", "Беговая дорожка", "Арбуз", "Качели", "Лыжня", "Мороженное", "Физкультура", "Клизма", "Джеки Чан", "Пылесос", "Дворецкий", "Гусеница", "Джойстик", "Твистер"};
    //41
    private final String mNormal[] = {"Сломленный духом", "Дельфин", "Подводная лодка", "Креветка", "Божья коровка", "Кокаиновый утренник", "Порнография", "Следопыт", "Волосатый примат", "Туалетный утёнок", "Слизняк", "Пнутая кошка", "Шаром покати", "Щедрый еврей", "Трубочист", "Нарцисс", "Конец", "Веселая молочница",
            "Стильный гопник", "Хвостатый зверь", "Падальщик", "Тираннозавр", "Качан", "Дискета", "Кавказская пленница", "Скунс холостяк", "Игра крокодил", "Мужские стринги", "Леденец", "Саждение на кол", "Киви", "Страшный запор", "Всадник без головы", "Тверк ботаника", "Полный привод", "Пейджер", "Инопланетянин",
            "Алхимик", "Параша", "Высунуть хобот", "Дешёвые понты", "Кочки", "Туман", "Воробей шизофреник", "Подработка ершиком", "Бревно", "Бомж", "Улитка", "Привет", "Запретный плод", "Котопес", "Пенопласт", "Молекула", "Хипстер", "Консьержка"};
    //55
    private final String mHard[] = {"Голубой песец", "Теща джигурды", "Галлюцинации девственницы", "Белая горячка", "Розовый тазик", "Уролог подхалим", "Аниме", "Паз", "Шизофрения", "Мурчать", "Стоять", "Жалобный писк", "Отпиленная благодать", "Чокнутый", "Самосвал", "Лишняя кнопка", "Раскрепощенный мопс",
            "Пузо карапуза", "Жижа", "Бутылка абсента", "Москвич", "Салки", "Пропаганда", "Сомелье фрилансер", "Симулировать", "Половая трещина", "Невесомость", "Родильное отделение", "Молчанка", "Самодельный сын", "Тварь", "Намыленный негр", "Букашка", "Передернуть затвор", "Розыгрыш", "Жук навозник", "Подлиза", "Портовая шлюпка",
            "Достать закладку", "Логика", "Сексуальная революция", "Добротная канализация", "Толерантность", "Массоны", "Смайлик", "Домогательство", "Реальность", "Бабник", "Человек", "Звезда", "Бешеная пчела", "Хоббит", "Колобок", "Паркур", "Баклажан"};
    //55
    private final String mInsane[] = {"Уединение с прищепкой", "Оценить по достоинству", "Эффектная блондинка", "Гламурный падонок", "Фетишист", "Личные пожитки", "Отрицание", "Трезвый кукушенок", "Издевательство", "Червяк задира", "Бесконечное ничто", "Мерзкий чудак", "Каракатица", "Смешарик", "Завтра", "Изображать", "Мастер над шептунами",
            "Проктолог затейник", "Богомол", "Член партии", "Горький хрен", "Приход прихожан", "Вялый глист", "Мученик", "Ночной горшок", "Карательная порка", "Доходяга за пивом", "Разумное одиночество", "Мокрый буйок", "Милая харя", "Уздечка конюха", "Пионер в засаде"};
    //32
    private final String mHYIP[] = {"Навальный", "Собянин", "Гироскутер", "Телеграм", "Путин", "Серия пенальти", "Хайп", "Крымский мост", "Это фиаско братан", "Дружко", "Ксения Собчак", "Ибица", "Трамп", "Цвет настроения синий", "Золотов", "Илон Маск", "Пенсионная реформа", "Майдан", "Дед мороз", "Версус", "Повестка", "Митинг",
            "Электросамокат", "Стендап"};
    private final int total = mEasy.length + mNormal.length + mHard.length + mInsane.length + mHYIP.length; //Колличество слов
    private final String mUsedSave = "SAVE_USED", exNumSave = "SAVE_NUM";
    private SoundPool mySound;
    private int soundClick, soundDisturbance; //Для звуков кнопки
    private int equal, num = 0, exNum = 0; //Счетчики для кнопки
    private DrawerLayout drawer; //Для обработки навбара
    private long pressedAgain; //Для сохранения времени нажатия
    private ImageButton imageButton; //Главная кнопка
    private TextView textView, textView2; //Главный текст
    private CheckBox checkEasy, checkNormal, checkHard, checkInsane, checkHYIP, checkPaging; //Чекбоксы в навбаре
    private String finalArray[]; //Массив со словами
    private SharedPreferences sPref; //Для сохранения использованных
    private Integer[] intsVolatile, ints = new Integer[total]; //Для перемешивания индексов слов
    private String[] mUsed = new String[total]; //Использованные слова

    private static <T> T[] concatAll(T[] first, T[]... rest) {  //Дженерик складывающий массивы
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getPreferences(MODE_PRIVATE);
        setContentView(R.layout.activity_main); //Ищем Viev элементы
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        checkEasy = findViewById(R.id.checkEasy);
        checkNormal = findViewById(R.id.checkNormal);
        checkHard = findViewById(R.id.checkHard);
        checkInsane = findViewById(R.id.checkInsane);
        checkHYIP = findViewById(R.id.checkHYIP);
        checkPaging = findViewById(R.id.checkPaging);

        Typeface tf = Typeface.createFromAsset(getAssets(), "font/ArbatDi.ttf"); //Подгружаем шрифт
        textView.setTypeface(tf); //Ставим шрифт
        textView2.setTypeface(tf);
        checkEasy.setTypeface(tf);
        checkNormal.setTypeface(tf);
        checkHard.setTypeface(tf);
        checkInsane.setTypeface(tf);
        checkHYIP.setTypeface(tf);
        checkPaging.setTypeface(tf);

        mySound = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
        soundClick = mySound.load(this, R.raw.btnm, 1);
        soundDisturbance = mySound.load(this, R.raw.btnm2, 1); //Находим звук

        for (int i = 0; i < mUsed.length; i++)
            mUsed[i] = "0";  //Заполняем массив использованных слов значениями
        loadText();  //Загружаем массив использованных слов если он есть (метод ниже)
        for (int i = 0; i < total; i++)
            ints[i] = i;  //Заполняем массив индексов слов
        List<Integer> lst = Arrays.asList(ints); //Представляем как список
        Collections.shuffle(lst);  //Перемешиваем
        ints = lst.toArray(ints); //Вставляем перемешанное
        finalArray = concatAll(mEasy, mNormal, mHard, mInsane, mHYIP); //Соединяем массивы в один (дженерик ниже)
    }

    public void newWord(View view) {
        textView2.setVisibility(View.GONE); //Скрываем текст правил
        if (checkPaging.isChecked() && (pressedAgain + 5000 > System.currentTimeMillis())) { //Если чекбокс "запретить перелистывания" включен и с времени приведущего нажатия прошло менее 5 секунд.

            textView.setText(getString(R.string.trying_to_list, finalArray[ints[num - 1]]));
            mySound.play(soundDisturbance, 1, 1, 1, 0, 1);

        } else {  //Bначе
            try { //Ловим исключение конца массива
                do {   //Цикл
                    equal = 0;   //Каждую итерацию надеемся на 0 совпадений
                    for (String i : mUsed)  //Перебираем элементы массива использованных слов
                        if (i.equals(finalArray[ints[num]])) { //Пока не найдем совпадения с настоящим словом
                            equal = 1;   //Если нашли то из цикла не выходим и меняем 0 совпадений на 1
                            break; //Но это слово нам уже не подходит, перебор заканчиваем
                        }
                    num = num + equal; //Если нашли совпадение, то повторяем цикл для следующего слова, пока не найдем не повторяющееся.
                }
                while (equal == 1); //Пока совпадения есть не заканчиваем

                textView.setText(finalArray[ints[num]]); //Выводим не использованное ранее слово

                mUsed[exNum] = finalArray[ints[num]];  //Записываем его в использованные
                exNum++;    //Счетчик для использованных (его обнуляет только сброс)
                num++;  //Счетчик для настоящих (его обнуляет перемешивание)

            } catch (ArrayIndexOutOfBoundsException exception) {   //Если слова кончились сообщаем об этом и открываем навбар
                Toast.makeText(this, R.string.end_of_array, Toast.LENGTH_SHORT).show();
                drawer.openDrawer(Gravity.START);
            }
        }

        mySound.play(soundClick, 1, 1, 1, 0, 1); //Звук нажатия кнопки
        pressedAgain = System.currentTimeMillis(); //Сохраняем время нажатия

    }

    public void navBar(View view) {   //Метод для кнопки открытия навбара (стрелка)
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
    }

    public void onCheckboxClicked(View view) {  //Если нажат чекбокс

        int tot = total;   //Создаем копии констант и приравниваем к ним переменные.
        String mEasyNullable[] = mEasy;
        String mNormalNullable[] = mNormal;
        String mHardNullable[] = mHard;
        String mInsaneNullable[] = mInsane;
        String mHYIPNullable[] = mHYIP;

        if (!checkEasy.isChecked()) {  //Если чекбокс пуст то..
            tot -= mEasy.length;   //Колличество элементов уменьшается
            mEasyNullable = new String[0];  //А массив обнуляется так, чтобы при его сложении он отсутствовал
        }
        if (!checkNormal.isChecked()) {  //Повторяем для каждого чекбокса
            tot -= mNormal.length;
            mNormalNullable = new String[0];
        }
        if (!checkHard.isChecked()) {
            tot -= mHard.length;
            mHardNullable = new String[0];
        }
        if (!checkInsane.isChecked()) {
            tot -= mInsane.length;
            mInsaneNullable = new String[0];
        }
        if (!checkHYIP.isChecked()) {
            tot -= mHYIP.length;
            mHYIPNullable = new String[0];
        }

        if (tot < 1) {    //Если ни один чекбокс не выделен
            tot = 10;      //Защищаем программу от падения
            textView.setText(R.string.nothing_checked);  //Намекаем что так не пойдет
            imageButton.setClickable(false);   //Защищаем программу от дурака
        } else {
            imageButton.setClickable(true);   //Снимаем защиту
        }

        intsVolatile = new Integer[tot];  //Создаем массив под новый размер
        for (int i = 0; i < tot; i++) {
            intsVolatile[i] = i;
        }     //Заполняем массив индексов слов
        List<Integer> lst = Arrays.asList(intsVolatile);   //Представляем как список
        Collections.shuffle(lst);  //Перемешиваем
        ints = lst.toArray(intsVolatile);  //Вставляем перемешанное в наш полноразмерный массив
        finalArray = concatAll(mEasyNullable, mNormalNullable, mHardNullable, mInsaneNullable, mHYIPNullable); //Соединяем массивы в один (дженерик выше)
        num = 0;    //Обнуляем нумерацию
    }


    private void saveText() {   //Сохраняем в Preferences
        StringBuilder save = new StringBuilder();   //В виде строки
        for (String i : mUsed) {
            save.append(i).append(",");
        }      //С разделителем ","
        sPref.edit()
                .putString(mUsedSave, save.toString())
                .putInt(exNumSave, exNum)  //И переменную счетчика exNum
                .apply();

    }

    private void loadText() {      //Загружаем из Preferences
        String savedText = sPref.getString(mUsedSave, "");
        StringTokenizer st = new StringTokenizer(savedText, ","); //Разделяем обратно по токену
        while (st.hasMoreTokens()) {
            for (int i = 0; i < total; i++) {
                mUsed[i] = st.nextToken();
            }      //И суем в массив
        }
        exNum = sPref.getInt(exNumSave, 0);  //Переменную просто грузим
    }


    @Override
    protected void onDestroy() {    //Сохраняем при закрытии
        super.onDestroy();

        saveText();
    }


    public void refresh(View view) {    //Метод для кнопки "работы с нуля"
        for (int i = 0; i < mUsed.length; i++)
            mUsed[i] = "0";   //Массив использованных обнуляем
        exNum = 0;    //Счетчики обнуляем
        num = 0;
        Toast.makeText(this, R.string.refreshed, Toast.LENGTH_SHORT).show();  //Уведомляем
    }
}

