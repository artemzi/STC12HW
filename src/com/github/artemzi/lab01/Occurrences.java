package com.github.artemzi.lab01;

/**
 * Рализовать следующий интерфейс:
 *
 * void getOccurencies(String[] sources, String[] words, String res) throws …;
 *
 * Многоточие означает необходимые для реализации исключения
 *
 * Функциональные требования: метод получает на вход массив адресов ресурсов (файлы, FTP, web-ссылки) и слов.
 * Необходимо в многопоточном режиме найти вхождения всех слов второго массива в ресурсы.
 * Если слово входит в предложение, это предложение добавляется в файл по адресу res.
 * При начале исполнения метода файл очищается (чтобы исключить наличие старой информации).
 *
 * Все ресурсы текстовые. Необходимо определить оптимальную производительность.
 * Возможны ситуации как с большим числом ресурсов (>2000 текстовых ресурсов каждый <2кб),
 * так и с очень большими ресурсами (ресурс>1ГБ).
 * Максимальный размер массива ресурсов 2000 элементов. Максимальный размер массива слов 2000 элементов.
 * Предложение это последовательность слов, начинающаяся с заглавной буквы и заканчивающаяся точкой,
 * вопросительным знаком, восклицательным знаком, или многоточием.
 * Внутри предложения могут находиться знаки препинания.
 * Слово это последовательность символов кириллических, либо латинских.
 */
public interface Occurrences {
    void getOccurences(String[] sources, String[] words, String res);
}
