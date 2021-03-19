package com.projeto.androideventos.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.projeto.androideventos.R;
import com.projeto.androideventos.adapter.ListaEventosAdapter;
import com.projeto.androideventos.model.Eventos;
import com.projeto.androideventos.util.AndroidUtils;
import com.projeto.androideventos.util.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.projeto.androideventos.R.string.agenda;
import static com.projeto.androideventos.util.Constantes.URL_API;

public class TelaInicialActivity extends AppCompatActivity {

    /**
     * OBJETIVO.......: INSTANCIAR COMPONENTES
     */
    Context contexto;
    List<Eventos> listaEventos;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associarComponentes();

        /**
         * OBJETIVO.......: ADICIONAR TITULO A TOOLBAR
         */
        collapsingToolbarLayout.setTitle(getString(agenda));

        /**
         * OBJETIVO.......: SOLICITAR DADOS APENAS SE TIVER INTERNET
         */
        if (AndroidUtils.isNetworkAvailable(getApplicationContext())) {

            /**
             * OBJETIVO.......: SOLICITAR DADOS / EXIBIR PROGRESS BAR
             * */
            progressBar.setVisibility(View.VISIBLE);
            getJsonApiEventos();

        } else {

            /**
             * OBJETIVO.......: CONEXAO EXIGIDA
             */
            progressBar.setVisibility(View.GONE);
            //AndroidUtils.msgToastSaida(TelaInicialActivity.this, getString(R.string.conexao_necessaria));
            AndroidUtils.msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.conexao_necessaria));
        }
    }

    /**
     * OBJETIVO.......: ASSOCIAR COMPONENTES
     */
    private void associarComponentes() {

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        recyclerView = findViewById(R.id.listaEventos);
        progressBar = findViewById(R.id.progressBar);

        listaEventos = new ArrayList<>();

        /**
         * OBJETIVO.......: LAYOUT DO RECYCLERVIEW
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(contexto, LinearLayoutManager.VERTICAL, false));
    }

    /**
     * OBJETIVO.......: SOLICITAR DADOS
     */
    private void getJsonApiEventos() {

        jsonArrayRequest = new JsonArrayRequest(
                URL_API,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /**
                         * OBJETIVO.......: TRATAR DADOS RECEBIDOS
                         */
                        progressBar.setVisibility(View.GONE);
                        getJsonApiEventosAfter(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        /**
                         * OBJETIVO.......: TRATAR ERROS AO SOLICITAR DADOS
                         */
                        error.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        //AndroidUtils.msgToastSaida(TelaInicialActivity.this, getString(R.string.erro_solicitacao));
                        AndroidUtils.msgSnackbarSaida(collapsingToolbarLayout, getString(R.string.erro_solicitacao));
                    }
                }
        );
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * OBJETIVO.......: TRATAR DADOS RECEBIDOS
     */
    private void getJsonApiEventosAfter(JSONArray arrayData) {

        for (int i = 0; i < arrayData.length(); i++) {

            Eventos evento = new Eventos();

            JSONObject jsonObject = null;

            try {
                /**
                 * OBJETIVO.......: TRANSFORMAR EM OBJETO
                 */
                jsonObject = arrayData.getJSONObject(i);
                evento.setPessoaEvento(jsonObject.getString(Constantes.JSON_PESSOA));
                evento.setDataEvento(jsonObject.getLong(Constantes.JSON_DATE));
                evento.setDescricaoEvento(jsonObject.getString(Constantes.JSON_DESCRICAO));
                evento.setImagemEvento(jsonObject.getString(Constantes.JSON_IMAGEM));
                evento.setLongitudeEvento(jsonObject.getDouble(Constantes.JSON_LONGITUDE));
                evento.setLatitudeEvento(jsonObject.getDouble(Constantes.JSON_LATITUDE));
                evento.setPrecoEvento(jsonObject.getDouble(Constantes.JSON_PRECO));
                evento.setTituloEvento(jsonObject.getString(Constantes.JSON_TITULO));
                evento.setIdEvento(jsonObject.getInt(Constantes.JSON_ID));
                listaEventos.add(evento);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * OBJETIVO.......: POPULAR RECYCLERVIEW
         */
        recyclerViewAdapter = new ListaEventosAdapter(listaEventos, TelaInicialActivity.this);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    /**
     * OBJETIVO.......: CASO CANCELE O CARREGAMENTO
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
}