package lmao;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class MoneyConverter {

    static void readText() throws Exception {
        File temp = new File("temp.temp");
        Scanner scannerTemp = new Scanner(temp);
        ArrayList<String> tempArray = new ArrayList<>();
        while (scannerTemp.hasNextLine()) {
            tempArray.add(scannerTemp.nextLine());
        }
        Object[] variables = tempArray.toArray();
        scannerTemp.close();
        temp.delete();
        File file = new File("Conversion Rates\\"+variables[0]+"-"+variables[1]+".json");
        Scanner scannerRatesTxt = new Scanner(file);
        ArrayList<String> arrayList = new ArrayList<>();
        while (scannerRatesTxt.hasNextLine()) {
            arrayList.add(scannerRatesTxt.nextLine());
        }
            Object[] list = arrayList.toArray();
            scannerRatesTxt.close();
        System.out.println(variables[2]+" "+variables[0]+" is equal to "+ Double.parseDouble((String) list[4]) * Double.parseDouble((String) variables[2]) +" "+variables[1]+".");
        file.delete();
        File conversionRatesFolder = new File("Conversion Rates");
        conversionRatesFolder.delete();

    }

    public static void main(String[] args) throws Exception {
        Scanner currencyInput = new Scanner(System.in);
	System.out.print("API key:- ");
	String apiKey = currencyInput.nextLine();
        System.out.print("Which currency do you want to convert?:- ");
        String firstCurrency = currencyInput.nextLine();
        System.out.print("To which currency do you want to convert this currency to?:- ");
        String secondCurrency = currencyInput.nextLine();
        System.out.print("How much " + firstCurrency.toUpperCase() + " do you want to convert?:- ");
        int amountToConvert = currencyInput.nextInt();
        FileWriter temp = new FileWriter("temp.temp");
        temp.write(firstCurrency.toUpperCase()+"\n");
        temp.write(secondCurrency.toUpperCase()+"\n");
        temp.write((Integer.toString(amountToConvert)));
        temp.close();

        File file = new File("Conversion Rates");
        file.mkdir();
        URL api = new URL("https://free.currconv.com/api/v7/convert?q="+firstCurrency+"_"+secondCurrency+"&compact=ultra&apiKey="+apiKey);
        Scanner fancyApiString = new Scanner(api.openStream());
        StringBuffer sb = new StringBuffer();
        while(fancyApiString.hasNext()) {
            sb.append(fancyApiString.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>:" , " ");
        int start = result.indexOf(":");
        int end = result.indexOf("}");

        FileWriter newFile = new FileWriter("Conversion Rates\\"+firstCurrency+"-"+secondCurrency+".json");
        newFile.write("{ \n");
        newFile.write(firstCurrency+" \n");
        newFile.write("1 \n");
        newFile.write(secondCurrency+" \n");
        newFile.write(result.substring(start+1, end)+" \n");
        newFile.write("}");
        newFile.close();


        readText();
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
}
