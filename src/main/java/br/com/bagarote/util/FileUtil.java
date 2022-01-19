package br.com.bagarote.util;

import br.com.bagarote.model.dto.request.CreateVenda;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    private static Gson gson = new Gson();

    private static String copyFile(String pathFile){

        StringBuilder sb = new StringBuilder();
        try {
            ClassPathResource resource = new ClassPathResource(pathFile);
            InputStream inputStream = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String linha;
            while((linha=br.readLine())!=null)
            {
                sb.append(linha);
            }
            br.close();
            isr.close();
            return sb.toString();
        }
        catch(IOException e)
        {
            System.out.println("Erro ao tentar ler Json : [FileUtil:copyFile]");
            e.printStackTrace();
        }
        return null;
    }

    public static CreateVenda fileJSONRead(String pathFile)  {

        String file = copyFile(pathFile);
        CreateVenda venda = gson.fromJson(file,CreateVenda.class);
        return venda;
    }
}
