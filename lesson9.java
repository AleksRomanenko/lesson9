package ru.geekbrains;

import java.util.Random;

public class lesson9 {

    /**
     * Проверяет заданный массив и вычисляет сумму всех элементов
     * только в том случае, если все элементы являются числами. Включены исключения.
     * @param array входящий строковый массив
     */
    public static void multiDimArray(String[][] array) {
        try {
            int sum = 0;

            // проверка размера массива
            for (String[] strings : array) {
                if (strings.length != 4 || array.length != 4) {
                    throw new MyArraySizeException("Неправильный размер массива", strings.length, array.length);
                }
            }

            // проверка того, все ли элементы являются целыми числами, вычисление суммы
            for (int y = 0; y < array.length; y++) {
                for (int x = 0; x < array[y].length; x++) {
                    try {
                        sum += Integer.parseInt(array[y][x]);
                    } catch (Exception e) {
                        throw new MyArrayDataException("Неверные данные", x,y,array[y][x]);
                    }
                }
            }
            System.out.printf("Хорошо, сумма всех чисел в этом массиве равна %d\n\n", sum);


        } catch (MyArraySizeException e) {
            System.out.printf("Неправильный размер массива: %d x %d (должно быть 4 x 4).\n\n", e.getySize(), e.getxSize());;
        } catch (MyArrayDataException e) {
            System.out.printf("Неверные данные в x = %d, y = %d. \"%s\" это не число\n\n", e.getX(), e.getY(), e.getCell());
        }
    }

    /**
     * Проверяет заданный массив и вычисляет сумму всех элементов
     * только в том случае, если все элементы являются числами. Исключения генерируются из других методов.
     * @param array входящий строковый массив
     * @throws MyArraySizeException когда размер массива не равен 4 x 4
     * @throws MyArrayDataException когда ячейка содержит что угодно, кроме целого числа
     */
    public static void multiDimArrayNoExceptions(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        // проверка размера массива
        for (String[] strings : array) {
            if (strings.length != 4 || array.length != 4) {
                throw new MyArraySizeException("Неправильный размер массива", strings.length, array.length);
            }
        }

        // проверка того, что все ли элементы являются целыми числами, вычисление суммы
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                try {
                    sum += Integer.parseInt(array[y][x]);
                } catch (Exception e) {
                    throw new MyArrayDataException("Неверные данные", x, y, array[y][x]);
                }
            }
        }
        System.out.printf("Хорошо, сумма всех чисел в этом массиве равна %d\n\n", sum);


    }

    /**
     * Отображает данный массив в виде матрицы
     * @param array входящий строковый массив
     */
    public static void displayArray(String[][] array) {
        for (String[] strings : array) {
            for (String i : strings) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        // создание массива 4 x 4 со случайными числами
        String[][] arr1 = new String[4][4];
        for (int y = 0; y < arr1.length; y++) {
            for (int x = 0; x < arr1[y].length; x++) {
                arr1[y][x] = String.valueOf(random.nextInt(10));
            }
        }

        // создание массива со случайными числами и неправильным размером
        String[][] arr2 = new String[3][4];
        for (int y = 0; y < arr2.length; y++) {
            for (int x = 0; x < arr2[y].length; x++) {
                arr2[y][x] = String.valueOf(random.nextInt(10));
            }
        }

        // создание массива 4 x 4 со случайными числами и одним неправильным значением
        String[][] arr3 = new String[4][4];
        for (int y = 0; y < arr3.length; y++) {
            for (int x = 0; x < arr3[y].length; x++) {
                arr3[y][x] = String.valueOf(random.nextInt(10));
            }
        }
        arr3[2][2] = "a";

        //запуск первого метода с двумя массивами
        displayArray(arr1);
        multiDimArray(arr1);
        displayArray(arr2);
        multiDimArray(arr2);

        // запуск второго метода с одним массивом
        try {
            displayArray(arr3);
            multiDimArrayNoExceptions(arr3);
        } catch (MyArraySizeException e) {
            System.out.printf("Неправильный размер массива: %d x %d (должно быть 4 x 4).\n\n", e.getySize(), e.getxSize());;
        } catch (MyArrayDataException e) {
            System.out.printf("Неверные данные в x = %d, y = %d. \"%s\" это не число\n\n", e.getX(), e.getY(), e.getCell());
        }
    }
}
    abstract class MyArrayException extends Exception {
        public MyArrayException(String messange) {
            super(messange);
        }

    }

    class MyArraySizeException extends MyArrayException {

        private final int xSize;
        private final int ySize;

        public int getxSize() {
            return xSize;
        }

        public int getySize() {
            return ySize;
        }

        public MyArraySizeException(String messange, int xSize, int ySize) {
            super(messange);
            this.xSize = xSize;
            this.ySize = ySize;
        }
    }

    class MyArrayDataException extends MyArrayException {

        private final int x;
        private final int y;

        private final String cell;

        public String getCell() {
            return cell;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public MyArrayDataException(String messange, int x, int y, String cell) {
            super(messange);
            this.x = x;
            this.y = y;
            this.cell = cell;
        }
    }
