package com.projeto.androideventos.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.projeto.androideventos.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class AndroidUtils {

    /**
     * COMPARTILHAR
     */
    public static void compartilharEvento(final Activity contexto, String evento) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, evento);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        contexto.startActivity(shareIntent);
    }


    /**
     * OBJETIVO.......: CHECAR SE HÁ CONEXÃO COM A INTERNET
     * @param contexto
     * @return
     */
    public static boolean isNetworkAvailable(Context contexto) {

        ConnectivityManager connectivity = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * OBJETIVO.......: DOWNLOAD PARA EXIBIÇÃO DAS IMAGENS
     */
    public static void downloadImageTask(String imagemDownload, int targetWidth, int targetHeight, int radius, int margin, ImageView imageView) {
           Picasso
                    .get()
                    // Imagem
                    .load(imagemDownload)
                    // Adicionar tamanho
                    .resize(targetWidth, targetHeight)
                    .centerInside()
                    // Adicionar bordas
                    .transform(new CircleTransform(radius, margin))
                    // exibir imagem antes do carregamento
                    .placeholder(R.drawable.agenda_png)
                    // se houver algum erro exibir essa imagem
                    .error(R.drawable.agenda_png)
                    // desabilitar cache NO_STORE
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    // desabilitar cache manter NO_STORE
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    // Saida = exibir a imagem em qual elemento
                    .into(imageView);
    }

    /**
     * OBJETIVO.......: CONVERTER TIMESTAMP TO DATE
     */
    public static String converterTimestampToDate(long dataTimestamp){
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getDefault();
        calendar.setTimeInMillis(dataTimestamp * 1000);
        calendar.add(Calendar.MILLISECOND, timeZone.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        Date dateTimeZone = (Date) calendar.getTime();
        return simpleDateFormat.format(dateTimeZone);
    }

    /**
     * OBJETIVO.......: MSG DE SAIDA TOAST
     * @param msg
     */
    public static void msgToastSaida(Context contexto, String msg) {

        Toast toastAlert = Toast.makeText(contexto, msg, Toast.LENGTH_LONG);
        LinearLayout linearLayoutAlert = (LinearLayout) toastAlert.getView();
        if (linearLayoutAlert.getChildCount() > 0) {
            TextView txtAlert = (TextView) linearLayoutAlert.getChildAt(0);
            txtAlert.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
        toastAlert.setGravity(Gravity.CENTER, 0, 0);
        toastAlert.show();
    }

    public static void msgSnackbarSaida(CollapsingToolbarLayout collapsingToolbarLayout, String msg) {
        Snackbar snackbar = Snackbar.make(collapsingToolbarLayout,msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * OBJETIVO.......: CONVERTER PARA REAL BRASILEIRO
     * @param precoEvento
     * @return
     */
    public static String converterMoeda(Double precoEvento) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(precoEvento);
    }

    /**
     * OBJETIVO.......: ABRIR GOOGLE MAPS PARA EXIBIR LOCALIZACAO
     * @param contexto
     * @param latitude
     * @param longitude
     */
    public static void exibirLocalizacao(Context contexto, Double latitude, Double longitude) {
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        String urlMapa = String.format(contexto.getResources().getString(R.string.url_maps), latitude.toString(), longitude.toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMapa));
        contexto.startActivity(intent);
    }

}
