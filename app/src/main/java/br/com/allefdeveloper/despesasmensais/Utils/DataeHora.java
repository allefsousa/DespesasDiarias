package br.com.allefdeveloper.despesasmensais.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by allef on 22/01/2018.
 */

public class DataeHora {
    public static int retornoHora(){

        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();



        String hora_atual = dateFormat_hora.format(data_atual);

        String[] obtendoHoraexata = hora_atual.split(":");
        int horaInteira = 0;
        horaInteira = Integer.parseInt(String.valueOf(obtendoHoraexata[0]));




        return horaInteira;
    }


    public  static String retornadaData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        return data_completa;
    }

}
