package smitedreal.moneyMake.Goverment;

import smitedreal.smiteddata.SDAT;

import java.io.File;
import java.util.List;

public class TotalMoney {
    private static SDAT data;
    public static Double TotalMoney;

    public static void SetupTM() {
        // Make new folder if it doesnt already exist
        File folder = new File("plugins/moneymake");
        if (!folder.exists()) folder.mkdirs();

        // Create the config file
        data = new SDAT("plugins/moneymake/total_money.txt");
        if (data.exists()) {
           data.clear();
           data.writeLine("Total Money: " + TotalMoney);
        } else {
            data.clear();
            data.writeLine("Total Money: 0.0");
        }
    }

    public static void AddMoney(Double money) {
        TotalMoney += money;
        data.clear();
        data.writeLine("Total Money: " + TotalMoney);
    }
    public static void RemoveMoney(Double money) {
        TotalMoney -= money;
        data.clear();
        data.writeLine("Total Money: " + TotalMoney);
    }
    public static Double GetTotalMoney() {
        return TotalMoney;
    }
    public static void LoadTotalMoney() {
        if (data.exists()) {
            Double money = data.getDouble(0);
            TotalMoney = money;
        } else {
            TotalMoney = 0.0;
        }
    }
}
