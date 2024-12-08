import java.awt.Color;

public class KernelFilter {

    // Возвращает новое изображение, которое применяет идентификационный фильтр
    // к данному изображению.
    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        // создаем три массива хранящих RGB значения пикселей от 0 до 255
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = picture.get(i, j); // получаем цвет пикселя
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
            }
        }
        // преобразуем массивы и собираем картинку
        Picture rpicture = new Picture(w, h);
        int[][] rred = new int[w][h];
        int[][] rgreen = new int[w][h];
        int[][] rblue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // граничные условия
                int a1 = i - 1;
                if (a1 < 0) a1 = a1 + w;
                if (a1 > w - 1) a1 = a1 - w;
                int b1 = j - 1;
                if (b1 < 0) b1 = b1 + h;
                if (b1 > h - 1) b1 = b1 - h;
                int a2 = i + 1;
                if (a2 < 0) a2 = a2 + w;
                if (a2 > w - 1) a2 = a2 - w;
                int b2 = j + 1;
                if (b2 < 0) b2 = b2 + h;
                if (b2 > h - 1) b2 = b2 - h;
                // формула фильтра
                rred[i][j] = (Math.round(
                        (red[i][j] * 4 + red[a1][b1] + 2 * red[a1][j] + red[a1][b2] + 2 * red[i][b1]
                                + 2 * red[i][b2] + red[a2][b1] + 2 * red[a2][j] + red[a2][b2])
                                * 1.0f / 16));
                if (rred[i][j] < 0) rred[i][j] = 0;
                if (rred[i][j] > 255) rred[i][j] = 255;
                rgreen[i][j] = (Math.round(
                        (green[i][j] * 4 + green[a1][b1] + 2 * green[a1][j] + green[a1][b2]
                                + 2 * green[i][b1] + 2 * green[i][b2] + green[a2][b1]
                                + 2 * green[a2][j] + green[a2][b2]) * 1.0f / 16));
                if (rgreen[i][j] < 0) rgreen[i][j] = 0;
                if (rgreen[i][j] > 255) rgreen[i][j] = 255;
                rblue[i][j] = (Math.round(
                        (blue[i][j] * 4 + blue[a1][b1] + 2 * blue[a1][j] + blue[a1][b2]
                                + 2 * blue[i][b1] + 2 * blue[i][b2] + blue[a2][b1] + 2 * blue[a2][j]
                                + blue[a2][b2]) * 1.0f / 16));
                if (rblue[i][j] < 0) rblue[i][j] = 0;
                if (rblue[i][j] > 255) rblue[i][j] = 255;
                rpicture.set(i, j, new Color(rred[i][j], rgreen[i][j], rblue[i][j]));
            }

        }
        return rpicture;
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        // создаем три массива хранящих RGB значения пикселей от 0 до 255
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = picture.get(i, j); // получаем цвет пикселя
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
            }
        }
        // преобразуем массивы и собираем картинку
        Picture rpicture = new Picture(w, h);
        int[][] rred = new int[w][h];
        int[][] rgreen = new int[w][h];
        int[][] rblue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a1 = i - 1;
                if (a1 < 0) a1 = a1 + w;
                if (a1 > w - 1) a1 = a1 - w;
                int b1 = j - 1;
                if (b1 < 0) b1 = b1 + h;
                if (b1 > h - 1) b1 = b1 - h;
                int a2 = i + 1;
                if (a2 < 0) a2 = a2 + w;
                if (a2 > w - 1) a2 = a2 - w;
                int b2 = j + 1;
                if (b2 < 0) b2 = b2 + h;
                if (b2 > h - 1) b2 = b2 - h;
                rred[i][j] = red[i][j] * 5 - red[a1][j] - red[i][b1] - red[i][b2] - red[a2][j];
                if (rred[i][j] < 0) rred[i][j] = 0;
                if (rred[i][j] > 255) rred[i][j] = 255;
                rgreen[i][j] = green[i][j] * 5 - green[a1][j] - green[i][b1] - green[i][b2]
                        - green[a2][j];
                if (rgreen[i][j] < 0) rgreen[i][j] = 0;
                if (rgreen[i][j] > 255) rgreen[i][j] = 255;
                rblue[i][j] = blue[i][j] * 5 - blue[a1][j] - blue[i][b1] - blue[i][b2]
                        - blue[a2][j];
                if (rblue[i][j] < 0) rblue[i][j] = 0;
                if (rblue[i][j] > 255) rblue[i][j] = 255;
                rpicture.set(i, j, new Color(rred[i][j], rgreen[i][j], rblue[i][j]));
            }
        }
        return rpicture;
    }


    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        // создаем три массива хранящих RGB значения пикселей от 0 до 255
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = picture.get(i, j); // получаем цвет пикселя
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
            }
        }
        // преобразуем массивы и собираем картинку
        Picture rpicture = new Picture(w, h);
        int[][] rred = new int[w][h];
        int[][] rgreen = new int[w][h];
        int[][] rblue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a1 = i - 1;
                if (a1 < 0) a1 = a1 + w;
                if (a1 > w - 1) a1 = a1 - w;
                int b1 = j - 1;
                if (b1 < 0) b1 = b1 + h;
                if (b1 > h - 1) b1 = b1 - h;
                int a2 = i + 1;
                if (a2 < 0) a2 = a2 + w;
                if (a2 > w - 1) a2 = a2 - w;
                int b2 = j + 1;
                if (b2 < 0) b2 = b2 + h;
                if (b2 > h - 1) b2 = b2 - h;
                rred[i][j] = red[i][j] * 8 - red[a1][b1] - red[a1][j] - red[a1][b2] - red[i][b1]
                        - red[i][b2] - red[a2][b1] - red[a2][j] - red[a2][b2];
                if (rred[i][j] < 0) rred[i][j] = 0;
                if (rred[i][j] > 255) rred[i][j] = 255;
                rgreen[i][j] = green[i][j] * 8 - green[a1][b1] - green[a1][j] - green[a1][b2]
                        - green[i][b1] - green[i][b2] - green[a2][b1] - green[a2][j]
                        - green[a2][b2];
                if (rgreen[i][j] < 0) rgreen[i][j] = 0;
                if (rgreen[i][j] > 255) rgreen[i][j] = 255;
                rblue[i][j] = blue[i][j] * 8 - blue[a1][b1] - blue[a1][j] - blue[a1][b2]
                        - blue[i][b1] - blue[i][b2] - blue[a2][b1] - blue[a2][j] - blue[a2][b2];
                if (rblue[i][j] < 0) rblue[i][j] = 0;
                if (rblue[i][j] > 255) rblue[i][j] = 255;
                rpicture.set(i, j, new Color(rred[i][j], rgreen[i][j], rblue[i][j]));
            }
        }
        return rpicture;
    }


    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        // создаем три массива хранящих RGB значения пикселей от 0 до 255
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = picture.get(i, j); // получаем цвет пикселя
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
            }
        }
        // преобразуем массивы и собираем картинку
        Picture rpicture = new Picture(w, h);
        int[][] rred = new int[w][h];
        int[][] rgreen = new int[w][h];
        int[][] rblue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a1 = i - 1;
                if (a1 < 0) a1 = a1 + w;
                if (a1 > w - 1) a1 = a1 - w;
                int b1 = j - 1;
                if (b1 < 0) b1 = b1 + h;
                if (b1 > h - 1) b1 = b1 - h;
                int a2 = i + 1;
                if (a2 < 0) a2 = a2 + w;
                if (a2 > w - 1) a2 = a2 - w;
                int b2 = j + 1;
                if (b2 < 0) b2 = b2 + h;
                if (b2 > h - 1) b2 = b2 - h;
                rred[i][j] = red[i][j] - 2 * red[a1][b1] - red[a1][j] - red[i][b1] + red[i][b2]
                        + red[a2][j] + 2 * red[a2][b2];
                if (rred[i][j] < 0) rred[i][j] = 0;
                if (rred[i][j] > 255) rred[i][j] = 255;
                rgreen[i][j] = green[i][j] - 2 * green[a1][b1] - green[a1][j] - green[i][b1]
                        + green[i][b2] + green[a2][j] + 2 * green[a2][b2];
                if (rgreen[i][j] < 0) rgreen[i][j] = 0;
                if (rgreen[i][j] > 255) rgreen[i][j] = 255;
                rblue[i][j] = blue[i][j] - 2 * blue[a1][b1] - blue[a1][j] - blue[i][b1]
                        + blue[i][b2] + blue[a2][j] + 2 * blue[a2][b2];
                if (rblue[i][j] < 0) rblue[i][j] = 0;
                if (rblue[i][j] > 255) rblue[i][j] = 255;
                rpicture.set(i, j, new Color(rred[i][j], rgreen[i][j], rblue[i][j]));
            }
        }
        return rpicture;
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        // создаем три массива хранящих RGB значения пикселей от 0 до 255
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = picture.get(i, j); // получаем цвет пикселя
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
            }
        }
        // преобразуем массивы и собираем картинку
        Picture rpicture = new Picture(w, h);
        int[][] rred = new int[w][h];
        int[][] rgreen = new int[w][h];
        int[][] rblue = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a1 = i - 4;
                if (a1 < 0) a1 = a1 + w;
                if (a1 > w - 1) a1 = a1 - w;
                int b1 = j - 4;
                if (b1 < 0) b1 = b1 + h;
                if (b1 > h - 1) b1 = b1 - h;
                int a2 = i - 3;
                if (a2 < 0) a2 = a2 + w;
                if (a2 > w - 1) a2 = a2 - w;
                int b2 = j - 3;
                if (b2 < 0) b2 = b2 + h;
                if (b2 > h - 1) b2 = b2 - h;
                int a3 = i - 2;
                if (a3 < 0) a3 = a3 + w;
                if (a3 > w - 1) a3 = a3 - w;
                int b3 = j - 2;
                if (b3 < 0) b3 = b3 + h;
                if (b3 > h - 1) b3 = b3 - h;
                int a4 = i - 1;
                if (a4 < 0) a4 = a4 + w;
                if (a4 > w - 1) a4 = a4 - w;
                int b4 = j - 1;
                if (b4 < 0) b4 = b4 + h;
                if (b4 > h - 1) b4 = b4 - h;
                int a5 = i + 1;
                if (a5 < 0) a5 = a5 + w;
                if (a5 > w - 1) a5 = a5 - w;
                int b5 = j + 1;
                if (b5 < 0) b5 = b5 + h;
                if (b5 > h - 1) b5 = b5 - h;
                int a6 = i + 2;
                if (a6 < 0) a6 = a6 + w;
                if (a6 > w - 1) a6 = a6 - w;
                int b6 = j + 2;
                if (b6 < 0) b6 = b6 + h;
                if (b6 > h - 1) b6 = b6 - h;
                int a7 = i + 3;
                if (a7 < 0) a7 = a7 + w;
                if (a7 > w - 1) a7 = a7 - w;
                int b7 = j + 3;
                if (b7 < 0) b7 = b7 + h;
                if (b7 > h - 1) b7 = b7 - h;
                int a8 = i + 4;
                if (a8 < 0) a8 = a8 + w;
                if (a8 > w - 1) a8 = a8 - w;
                int b8 = j + 4;
                if (b8 < 0) b8 = b8 + h;
                if (b8 > h - 1) b8 = b8 - h;
                rred[i][j] = Math.round(
                        (red[a1][b1] + red[a2][b2] + red[a3][b3] + red[a4][
                                b4] + red[i][j] + red[a5][b5] + red[a6][b6]
                                + red[a7][b7] + red[a8][b8]) * 1.0f / 9);
                if (rred[i][j] < 0) rred[i][j] = 0;
                if (rred[i][j] > 255) rred[i][j] = 255;
                rgreen[i][j] = Math.round(
                        (green[a1][b1] + green[a2][b2] + green[a3][b3] + green[a4][
                                b4] + green[i][j] + green[a5][b5] + green[a6][b6]
                                + green[a7][b7] + green[a8][b8]) * 1.0f / 9);
                if (rgreen[i][j] < 0) rgreen[i][j] = 0;
                if (rgreen[i][j] > 255) rgreen[i][j] = 255;
                rblue[i][j] = Math.round(
                        (blue[a1][b1] + blue[a2][b2] + blue[a3][b3] + blue[a4][
                                b4] + blue[i][j] + blue[a5][b5] + blue[a6][b6]
                                + blue[a7][b7] + blue[a8][b8]) * 1.0f / 9);
                if (rblue[i][j] < 0) rblue[i][j] = 0;
                if (rblue[i][j] > 255) rblue[i][j] = 255;
                rpicture.set(i, j, new Color(rred[i][j], rgreen[i][j], rblue[i][j]));
            }
        }
        return rpicture;
    }

    // Тестовый клиент (без оценки).
    // в папке проекта im1.png = args[0];
    // https://coursera.cs.princeton.edu/introcs/assignments/oop1/baboon.png = args[0];
    public static void main(String[] args) {
        String filename = args[0];
        Picture pic = new Picture(filename);
        (gaussian(pic)).show();
        (sharpen(pic)).show();
        (laplacian(pic)).show();
        (emboss(pic)).show();
        (motionBlur(pic)).show();
    }
}

