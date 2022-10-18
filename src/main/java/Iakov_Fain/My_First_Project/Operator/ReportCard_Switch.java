package Iakov_Fain.My_First_Project.Operator;


public class ReportCard_Switch {
    public char convertGrades(int testResult) {
        char grade;
        if (testResult >= 90) {
            grade = 'A';
        } else if (testResult >= 80 && testResult < 90) {
            grade = 'B';
        } else if (testResult >= 70 && testResult < 80) {
            grade = 'C';
        } else {
            grade = 'D';
        }
        return grade;
    }


    public static void main(String[] args) {
        ReportCard_Switch rc = new ReportCard_Switch();
        char yourGrade = rc.convertGrades(87);
        switch (yourGrade) {
            case 'A':
                System.out.println("Превосходная работа!");
                break;
            case 'B':
                System.out.println("Хорошая работа!");
                break;
            case 'C':
                System.out.println("Надо подтянуть знания!");
                break;
            case 'D':
                System.out.println("Будь посерьёзнее!");
                break;
        }
    }

}
