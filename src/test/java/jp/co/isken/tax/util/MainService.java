package jp.co.isken.tax.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* �e�X�g�ΏۃR�[�h */
public class MainService {
    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("���j���[��I�����Ă��������B");
        try {
            BufferedReader stdReader = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < 2; i++) {
                System.out.println("INPUT: ");
                String line;
                line = stdReader.readLine();
                System.out.println("OUTPUT: " + line);
            }
            stdReader.close();
            System.out.println("PROGRAM END");
        } catch (Exception e) {
            e.getStackTrace();
            System.exit(-1); // �v���O�������I��
        }
    }
}