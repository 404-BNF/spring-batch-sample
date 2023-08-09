package dev.sk.springbatchsamplev5upgrade.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import dev.sk.springbatchsamplev5upgrade.model.Person;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FileUtils {
    CSVReader csvReader;
    CSVWriter csvWriter;
    final String inputFilePath = "input/";
    String inputFileName = "input.csv";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyy");
    final String outputFilePath = "output/";
    String outputFileName = "output.csv";
    public FileUtils(){
        /*System.out.println("Opening Reader from Constr...");
        csvReader = new CSVReader(new FileReader(inputFilePath));
        System.out.println("Opening Writer from Constr..");
        File out = new File(outputFilePath);
        out.mkdirs();
        out = new File(outputFilePath + outputFileName);
        if (!out.exists()) out.createNewFile();
        csvWriter = new CSVWriter(new FileWriter(out));*/
    }

    public void openReader()throws Exception{
        System.out.println("Opening Reader..");
        csvReader = new CSVReader(new FileReader(inputFilePath + inputFileName));
    }
    public void closeReader()throws Exception{
        System.out.println("Closing Reader..");
        csvReader.close();
    }
    public void openWriter() throws Exception{
        System.out.println("Opening Writer..");
        File out = new File(outputFilePath);
        out.mkdirs();
        out = new File(outputFilePath + outputFileName);
        if (!out.exists()) out.createNewFile();
        csvWriter = new CSVWriter(new FileWriter(out));
    }
    public void closeWriter() throws Exception{
        System.out.println("Closing Writer..");
        csvWriter.close();
    }

    public Person read()throws Exception{
        Person person = null;
        String[] line = csvReader.readNext();
        if (line!=null){
            person = new Person();
            person.setName(line[0].trim());
            person.setDob(LocalDate.parse(line[1].trim(),formatter));
        }
        return  person;
    }

    public void write(Person person){
        csvWriter.writeNext(toList(person));
    }
    public String[] toList(Person person){
        String[] str = new String[2];
        str[0] = person.getName();
        str[1] = person.getAge()+"";
        return str;
    }

}
