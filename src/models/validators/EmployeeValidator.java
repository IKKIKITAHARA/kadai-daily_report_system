package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class EmployeeValidator {
    public static List<String> validate(Employee e,
            Boolean code_duplicate_flag, Boolean password_check_flag) {

        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(e.getCode(), code_duplicate_flag);
        if (!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(e.getName());
        if (!name_error.equals("")) {
            errors.add(name_error);
        }
        String password_error = _validatePassword(e.getPassword(), password_check_flag);
        if (!password_error.equals("")) {
            errors.add(password_error);

        }
        return errors;
    }

    //if条件を満たした場合、 String型の値"社員・・・"をValidateCodeメソッドを通じてString型code_errorに格納する
private static String _validateCode(String code, Boolean code_duplicate_check_flag){
     if(code == null || code.equals("")){
         return "社員番号を入力してください。";
     }
    //すでにDBに登録されている社員番号との重複チェックもする！
     if(code_duplicate_check_flag){
         EntityManager em = DBUtil.createEntityManager();
         /*createNamedQueryメソッドを使ってDBからmodelクラスで定義した"check..."を実行し、Longクラスにセット？
          パラメーターに"code”名の値codeをセット
          1件ずつ取り出して、employees_countへ格納*/
         long employees_count = (long)em.createNamedQuery("checkRegisteredCode",Long.class)
                                  .setParameter("code", code)
                                  .getSingleResult();
         em.close();
         if(employees_count >0){
             /*_validateCodeに値を返し、code_duplicate_check_flugメソッドを
             を通じて、code_errorに返す*/
             return"入力された社員番号の情報はすでに存在しています。";
         }
}
     return "";
}

    private static String _validateName(String name) {
        if (name == null || name.equals("")) {//if条件を満たした場合、String型のname_errorに格納する。
            return "氏名を入力しください。";
        }

        return "";
    }

    //PWの必須入力をチェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
