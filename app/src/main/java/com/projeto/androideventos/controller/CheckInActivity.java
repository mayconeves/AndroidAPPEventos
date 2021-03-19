package com.projeto.androideventos.controller;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.projeto.androideventos.R;
import com.projeto.androideventos.util.AndroidUtils;
import com.projeto.androideventos.util.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.projeto.androideventos.R.string.check_in;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * OBJETIVO.......: INSTANCIAR COMPONENTES
     */
    int idEvento;
    String email;
    String nome;
    String tituloEvento;
    String urlImagem;
    EditText txtEmail;
    EditText txtNome;
    TextView txtTitulo;
    Button btnEnviar;
    ProgressBar progressBar;
    ImageView imgEvento;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    LinearLayout linearCheckIn;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ckeck_in);

        /**
         * OBJETIVO.......: ASSOCIAR COMPONENTES
         */
        associarComponentes();

        /**
         * OBJETIVO.......: ADICIONAR TITULO A TOOLBAR
         */
        collapsingToolbarLayout.setTitle(getString(check_in));

        /**
         * OBJETIVO.......: RECUPERAR DADOS DA INTENT
         * POPULAR OS RESPECTIVOS CAMPOS
         */
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idEvento     = extras.getInt("idEvento");
            urlImagem    = extras.getString("imagemEvento");
            tituloEvento = extras.getString("tituloEvento");

            if (urlImagem != null)
                /**
                 * OBJETIVO.......: CARREGAR IMAGEM
                 */
                AndroidUtils.downloadImageTask(urlImagem, 1080, 480, 10, 0, imgEvento);

            if(tituloEvento != null)
                txtTitulo.setText(tituloEvento);
        }
    }

    /**
     * ASSOCIAR COMPONENTES DA INTERFACE
     */
    private void associarComponentes() {

        collapsingToolbarLayout     = findViewById(R.id.collapsingToolbarCheckIn);
        progressBar                 = findViewById(R.id.progressBarCheckIn);
        txtNome                     = findViewById(R.id.txtNomeCheckIn);
        txtEmail                    = findViewById(R.id.txtEmailCheckIn);
        btnEnviar                   = findViewById(R.id.btnEnviar);
        imgEvento                   = findViewById(R.id.imageCheckIn);
        txtTitulo                   = findViewById(R.id.txvTituloCheckIn);
        linearCheckIn               = findViewById(R.id.linearCheckIn);

        btnEnviar.setOnClickListener(this);
    }

    /**
     * OBJETIVO.......: ENVIAR DADOS PARA O ENDPOINT
     */
    private void enviarDadosCheckIn(JSONArray jsonDados) {

        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                Constantes.URL_API_CHECK_IN,
                jsonDados,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        /**
                         * OBJETIVO.......: DADOS ENVIADO COM SUCESSO
                         */
                        progressBar.setVisibility(View.GONE);
                        //AndroidUtils.msgToastSaida(CheckInActivity.this, getString(R.string.check_in_sucesso));
                        msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.check_in_sucesso));
                        /**
                         * OBJETIVO.......: IR PARA TELA INICIAL
                         */
                        //irParaTelaInicial();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        /**
                         * OBJETIVO.......: TRATAMENTO DE ERROS
                         */
                        progressBar.setVisibility(View.GONE);

                        //"Max number of elements reached for this resource!"
                        if(error.networkResponse.statusCode == 400){
                            //AndroidUtils.msgToastSaida(CheckInActivity.this, getString(R.string.erro_salvar));
                            msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.erro_salvar));
                            /**
                             * OBJETIVO.......: IR PARA TELA INICIAL
                             */
                        } else {
                            //AndroidUtils.msgToastSaida(CheckInActivity.this, getString(R.string.erro_solicitacao));
                            msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.erro_solicitacao));
                            /**
                             * OBJETIVO.......: IR PARA TELA INICIAL
                             */
                        }

                        /**
                         * OBJETIVO.......: IR PARA TELA INICIAL
                         */
                        //irParaTelaInicial();

                        /**
                         * TODAS AS TENTATIVAS RESULTARAM EM ERRO AO ENVIAR
                         * "Max number of elements reached for this resource!"
                         * SUPONDO que ESSA SERiA A RESPOSTA DE CONFIRMACAO DO CHECK-IN
                         * {"status": true}
                         */
                        /*
                        JSONArray jsonResposta = new JSONArray();
                        JSONObject dadosResposta = new JSONObject();
                        try {
                            dadosResposta.put("status", true);
                            jsonResposta.put(dadosResposta);
                            //Log.i("RESPONSE", "Dados response" + jsonResposta.toString());
                            AndroidUtils.msgToastSaida(CheckInActivity.this, getString(R.string.check_in_sucesso));
                            irParaTelaInicial();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                    }
                }
        );
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * IR PARA A TELA INICIAL
     * FECHAR TODAS AS TELAS
     */
    private void irParaTelaInicial() {
        /*
        Intent intent = new Intent(CheckInActivity.this, TelaInicialActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        */


        /**
         * TEMPO PARA EXIBIR SNACKBAR E FECHAR A JANELA
         */
        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2500);
         */

        finish();
    }

    /**
     * OBJETIVO.......: TRATAMENTO DE CLICKS
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEnviar:
                metodoEnviar();
                break;
        }
    }

    /**
     * OBJETIVO.......: METODO ENVIAR EFETIVO
     */
    private void metodoEnviar() {

        /**
         * OBJETIVO.......: VALIDAR DADOS ANTES DO ENVIO
         */
        boolean validar = true;
        nome = txtNome.getText().toString();
        email = txtEmail.getText().toString();

        if(nome.equals("")){
            txtNome.setError(getString(R.string.campo_obrigatorio));
            validar = false;
        }

        if(email.equals("")){
            txtEmail.setError(getString(R.string.campo_obrigatorio));
            validar = false;
        }

        if(validar){

            /**
             * OBJETIVO.......: OCULTAR ELEMENTOS APOS O ENVIO / EXIBIR PROGRESSO
             */
            linearCheckIn.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            /**
             * OBJETIVO.......: INSTRUCOES
             *
             * Para realizar o check-in faça um POST no seguinte endereço: http://5f5a8f24d44d640016169133.mockapi.io/api/checkin
             * O POST deve conter os dados do interessado (Nome, e-mail) e o id do evento.
             * Ex:{ "eventId": "1", "name": "Otávio", "email": "otavio_souza@..." }
             */
            JSONArray jsonDados = new JSONArray();
            JSONObject dados = new JSONObject();

            try {
                dados.put("eventId", String.valueOf(idEvento));
                dados.put("name", nome);
                dados.put("email", email);
                jsonDados.put(dados);
                //Log.i("ENVIAR", "Dados " + jsonDados.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /**
             * OBJETIVO.......: ENVIAR APENAS SE TIVER INTERNET
             */
            if(AndroidUtils.isNetworkAvailable(CheckInActivity.this)) {
                /**
                 * OBJETIVO.......: ENVIAR DADOS
                 */
                enviarDadosCheckIn(jsonDados);
            } else {
                /**
                 * OBJETIVO.......: CONEXAO EXIGIDA
                 */
                progressBar.setVisibility(View.GONE);
                //AndroidUtils.msgToastSaida(CheckInActivity.this, getString(R.string.conexao_necessaria));
                msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.conexao_necessaria));
            }
        }
    }

    /**
     * SNACKBAR ACTION
     * @param collapsingToolbarLayout
     * @param msg
     */
    private void msgSnackbarSaida(CollapsingToolbarLayout collapsingToolbarLayout, String msg) {
        Snackbar snackbar = Snackbar
                .make(collapsingToolbarLayout, msg, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.fechar), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        irParaTelaInicial();
                    }
                });
        snackbar.setActionTextColor(getResources().getColor(R.color.accent));
        snackbar.show();
    }
}