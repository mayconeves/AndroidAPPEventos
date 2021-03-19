package com.projeto.androideventos.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projeto.androideventos.R;
import com.projeto.androideventos.model.Eventos;
import com.projeto.androideventos.util.AndroidUtils;
import com.projeto.androideventos.util.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.projeto.androideventos.R.string.detalhes;

public class DetalhesActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * OBJETIVO.......: INSTANCIAR COMPONENTES
     */
    int idEvento;
    String dataEvento = null;
    String descricao = null;
    String preco = null;
    String tituloEvento = null;
    String urlImagem = null;
    List<Eventos> listaEvento;
    Double longitude;
    Double latitude;
    TextView txvDataEvento;
    TextView txvDescricao;
    TextView txvTitulo;
    TextView txvPreco;
    ImageView imagemDetalhes;
    FloatingActionButton fabCheckIn;
    FloatingActionButton fabCompartilhar;
    FloatingActionButton fabLocalizacao;
    LinearLayout linearDetalhes;
    RelativeLayout relativeLayoutDetalhes;
    ProgressBar progressBar;
    RequestQueue requestQueue;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        /**
         * OBJETIVO.......: ASSOCIAR COMPONENTES
         */
        associarComponentes();

        /**
         * OBJETIVO.......: ADICIONAR TITULO A TOOLBAR
         */
        collapsingToolbarLayout.setTitle(getString(detalhes));

        /**
         * OBJETIVO.......: RECUPERAR DADOS ENVIADOS PELA INTENT
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idEvento = extras.getInt("idEvento");

            /**
             * OBJETIVO.......: CARREGAR DADOS APENAS SE TIVER INTERNET
             */
            if (AndroidUtils.isNetworkAvailable(getApplicationContext())) {
                /**
                 * OBJETIVO.......: CARREGAR DADOS / EXIBIR PROGRESS
                 */
                progressBar.setVisibility(View.VISIBLE);
                getJsonApiEventosDetalhes(idEvento);
            } else {
                /**
                 * OBJETIVO.......: CONEXAO EXIGIDA
                 */
                progressBar.setVisibility(View.GONE);
                //AndroidUtils.msgToastSaida(DetalhesActivity.this, getString(R.string.conexao_necessaria));
                AndroidUtils.msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.conexao_necessaria));
            }
        }
    }

    /**
     * OBJETIVO.......: ASSOCIAR COMPONENTES
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void associarComponentes() {

        collapsingToolbarLayout     = findViewById(R.id.collapsingToolbarDetalhes);
        progressBar                 = findViewById(R.id.progressBarDetalhes);
        txvTitulo                   = findViewById(R.id.txvTitulo);
        imagemDetalhes              = findViewById(R.id.imageDetalhes);
        txvDataEvento               = findViewById(R.id.txvData);
        txvDescricao                = findViewById(R.id.txvDescricao);
        txvPreco                    = findViewById(R.id.txvPreco);
        fabCheckIn                  = findViewById(R.id.fabCheckIn);
        fabCompartilhar             = findViewById(R.id.fabCompartilhar);
        fabLocalizacao              = findViewById(R.id.fabLocalizacao);
        linearDetalhes              = findViewById(R.id.linearDetalhes);
        relativeLayoutDetalhes      = findViewById(R.id.relativelayoutDetalhes);
        //toolbar                     = findViewById(R.id.toolbarDetalhes);

        listaEvento                 = new ArrayList<>();

        fabCheckIn.setOnClickListener(this);
        fabCompartilhar.setOnClickListener(this);
        fabLocalizacao.setOnClickListener(this);

        /**
         * OBJETIVO.......: OCULTAR ANTES DO CARREGAMENTO DOS DADOS
         */
        linearDetalhes.setVisibility(View.INVISIBLE);
        relativeLayoutDetalhes.setVisibility(View.INVISIBLE);
//        setSupportActionBar(toolbar);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbarDetalhes));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * OBJETIVO.......: SOLICITAR DADOS
     */
    private void getJsonApiEventosDetalhes(int idEvento) {

        String urlSolicitacao = Constantes.URL_API + "/" + idEvento;

        /**
         * OBJETIVO.......: SOLICITACAO
         */
        StringRequest stringRequest = new StringRequest(
                urlSolicitacao,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {
                            try {
                                /**
                                 * CORRIGIR ENCODE
                                 */
                                response = new String(response.getBytes("ISO-8859-1"), "UTF-8");

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        /**
                         * OBJETIVO.......: TRATAR DADOS
                         */
                        getJsonApiEventosDetalhesAfter(response);
                        progressBar.setVisibility(View.GONE);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        /**
                         * OBJETIVO.......: TRATAR ERRO
                         */
                        error.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        //AndroidUtils.msgToastSaida(DetalhesActivity.this, getString(R.string.erro_solicitacao));
                        AndroidUtils.msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.erro_solicitacao));
                    }
                }
        );
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    /**
     * OBJETIVO.......: TRATAR DADOS RECEBIDOS
     */
    private void getJsonApiEventosDetalhesAfter(String stringData) {

        Eventos evento = new Eventos();

        JSONObject jsonObject = null;

        try {
            /**
             * OBJETIVO.......: TRANSFORMAR EM OBJETO
             */
            jsonObject = new JSONObject(stringData);
            evento.setPessoaEvento(jsonObject.getString(Constantes.JSON_PESSOA));
            evento.setDataEvento(jsonObject.getLong(Constantes.JSON_DATE));
            evento.setDescricaoEvento(jsonObject.getString(Constantes.JSON_DESCRICAO));
            evento.setImagemEvento(jsonObject.getString(Constantes.JSON_IMAGEM));
            evento.setLongitudeEvento(jsonObject.getDouble(Constantes.JSON_LONGITUDE));
            evento.setLatitudeEvento(jsonObject.getDouble(Constantes.JSON_LATITUDE));
            evento.setPrecoEvento(jsonObject.getDouble(Constantes.JSON_PRECO));
            evento.setTituloEvento(jsonObject.getString(Constantes.JSON_TITULO));
            evento.setIdEvento(jsonObject.getInt(Constantes.JSON_ID));
            listaEvento.add(evento);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * OBJETIVO.......: EXIBIR LAYOUT
         */
        linearDetalhes.setVisibility(View.VISIBLE);
        relativeLayoutDetalhes.setVisibility(View.VISIBLE);

        /**
         * OBJETIVO.......: POPULAR COMPONENTES COM OS DADOS RECEBIDOS
         */

        /**
         * OBJETIVO.......: CARREGAR IMAGEM
         */
        urlImagem = listaEvento.get(0).getImagemEvento();
        if (urlImagem != null)
            AndroidUtils.downloadImageTask(urlImagem, 1080, 480, 10, 0, imagemDetalhes);

        tituloEvento = listaEvento.get(0).getTituloEvento();
        txvTitulo.setText(Html.fromHtml(tituloEvento));

        dataEvento = AndroidUtils.converterTimestampToDate(listaEvento.get(0).getDataEvento());
        txvDataEvento.setText(dataEvento);


        /**
         * OBJETIVO.......: CORRIGIR QUEBRA DE LINHA
         */
        descricao = listaEvento.get(0).getDescricaoEvento();
        String descricaoLineBreak = descricao.replaceAll("\n", "<br>");
        SpannableString spannableString = new SpannableString(Html.fromHtml(descricaoLineBreak));
        txvDescricao.setText(spannableString);

        /**
         * OBJETIVO.......: CONVERTER PARA O FORMATO REAL BRASILEIRO
         */
        preco = AndroidUtils.converterMoeda(listaEvento.get(0).getPrecoEvento());
        txvPreco.setText(preco);

        longitude = listaEvento.get(0).getLongitudeEvento();
        latitude = listaEvento.get(0).getLatitudeEvento();
    }

    /**
     * OBJETIVO.......: CASO SEJA CANCELADO
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            // Caso queira cancelar durante a solicitacao
            // encerrando o processo de carregamento
            requestQueue.cancelAll("EVENTOS");
        }
    }

    /**
     * OBJETIVO.......: TRATAMENTO DE CLICKS
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabCompartilhar:

                /**
                 * OBJETIVO.......: PASSAR LONGITE E LATITUDE PARA A URL
                 */
                String urlMapa = String.format(getString(R.string.url_maps), latitude.toString(), longitude.toString());

                /**
                 * OBJETIVO.......: CRIAR TEXTO A SER COMPARTILHADO
                 */
                String textoCompartilhar =
                        tituloEvento + "\n" +
                        dataEvento + "\n" +
                        preco + "\n\n" +
                        descricao + "\n\n" + urlMapa;
                /**
                 * OBJETIVO.......: COMPARTILHAR
                 */
                AndroidUtils.compartilharEvento(DetalhesActivity.this, textoCompartilhar);
                break;
            case R.id.fabLocalizacao:
                /**
                 * OBJETIVO.......: ABRIR GOOGLE MAPS
                 */
                AndroidUtils.exibirLocalizacao(DetalhesActivity.this, latitude, longitude);
                break;
            case R.id.fabCheckIn:
                /**
                 * OBJETIVO.......: IR PARA TELA DE CHECK-IN
                 */
                irParaCheckIn();
                break;
        }
    }

    /**
     * OBJETIVO.......: IR PARA A TELA DE CHECK-IN
     */
    private void irParaCheckIn() {
        Intent intent;
        intent = new Intent(DetalhesActivity.this, CheckInActivity.class);
        intent.putExtra("idEvento", idEvento);
        intent.putExtra("imagemEvento", urlImagem);
        intent.putExtra("tituloEvento", tituloEvento);
        startActivity(intent);
    }
}