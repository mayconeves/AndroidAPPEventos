package com.projeto.androideventos.model;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OBJETIVO.......: TESTAR MODEL
 * ESTRUTURA DOS DADOS A SEREM RECEBIDOS
 *   {
 *     "people": [],
 *     "date": 1534784400,
 *     "description": "Toda quarta-feira, das 17h às 22h, encontre a feira mais charmosa de produtos frescos, naturais e orgânicos no estacionamento do Shopping. Sintonizado com a tendência crescente de busca pela alimentação saudável, consumo consciente e qualidade de vida. \n\nAs barracas terão grande variedade de produtos, como o shiitake cultivado em Ibiporã há mais de 20 anos, um sucesso na mesa dos que não abrem mão do saudável cogumelo na dieta. Ou os laticínios orgânicos da Estância Baobá, famosos pelo qualidade e modo de fabricação sustentável na vizinha Jaguapitã. Também estarão na feira as conhecidas compotas e patês tradicionais da Pousada Marabú, de Rolândia.\n\nA feira do Catuaí é uma nova opção de compras de produtos que não são facilmente encontrados no varejo tradicional, além de ótima pedida para o descanso de final de tarde em família e entre amigos. E com o diferencial de ser noturna, facilitando a vida dos consumidores que poderão sair do trabalho e ir direto para a “Vila Verde”, onde será possível degustar delícias saudáveis nos bistrôs, ouvir música ao vivo, levar as crianças para a diversão em uma estação de brinquedos e relaxar ao ar livre.\n\nEXPOSITORES DA VILA VERDE CATUAÍ\n\nCraft Hamburgueria\nNido Pastíficio\nSabor e Saúde\nTerra Planta\nEmpório da Papinha\nEmpório Sabor da Serra\nBoleria Dom Leonardi\nCoisas que te ajudam a viver\nPatês da Marisa\nMarabú\nBaobá\nAkko\nCervejaria Amadeus\n12 Tribos\nParr Kitchen\nHorta Fazenda São Virgílio\nHorta Chácara Santo Antonio\nSur Empanadas\nFit & Sweet\nSK e T Cogumelos\nDos Quintana\n\nLocal: Estacionamento (entrada principal do Catuaí Shopping Londrina)\n\n\nAcesso à Feira gratuito.",
 *     "image": "https://i2.wp.com/assentopublico.com.br/wp-content/uploads/2017/07/Feira-de-alimentos-org%C3%A2nicos-naturais-e-artesanais-ganha-um-novo-espa%C3%A7o-em-Ribeir%C3%A3o.jpg",
 *     "longitude": -51.2148497,
 *     "latitude": -30.037878,
 *     "price": 19,
 *     "title": "Feira de Produtos Naturais e Orgânicos",
 *     "id": "4"
 *   }
 * ]
 */

class EventosTest {


    //String[] pessoaEvento = new String[];
    long dataEvento         = 1534784400;
    String descricaoEvento  = "Toda quarta-feira, das 17h às 22h, encontre a feira mais charmosa de produtos frescos, naturais e orgânicos no estacionamento do Shopping. Sintonizado com a tendência crescente de busca pela alimentação saudável, consumo consciente e qualidade de vida. \n\nAs barracas terão grande variedade de produtos, como o shiitake cultivado em Ibiporã há mais de 20 anos, um sucesso na mesa dos que não abrem mão do saudável cogumelo na dieta. Ou os laticínios orgânicos da Estância Baobá, famosos pelo qualidade e modo de fabricação sustentável na vizinha Jaguapitã. Também estarão na feira as conhecidas compotas e patês tradicionais da Pousada Marabú, de Rolândia.\n\nA feira do Catuaí é uma nova opção de compras de produtos que não são facilmente encontrados no varejo tradicional, além de ótima pedida para o descanso de final de tarde em família e entre amigos. E com o diferencial de ser noturna, facilitando a vida dos consumidores que poderão sair do trabalho e ir direto para a “Vila Verde”, onde será possível degustar delícias saudáveis nos bistrôs, ouvir música ao vivo, levar as crianças para a diversão em uma estação de brinquedos e relaxar ao ar livre.\n\nEXPOSITORES DA VILA VERDE CATUAÍ\n\nCraft Hamburgueria\nNido Pastíficio\nSabor e Saúde\nTerra Planta\nEmpório da Papinha\nEmpório Sabor da Serra\nBoleria Dom Leonardi\nCoisas que te ajudam a viver\nPatês da Marisa\nMarabú\nBaobá\nAkko\nCervejaria Amadeus\n12 Tribos\nParr Kitchen\nHorta Fazenda São Virgílio\nHorta Chácara Santo Antonio\nSur Empanadas\nFit & Sweet\nSK e T Cogumelos\nDos Quintana\n\nLocal: Estacionamento (entrada principal do Catuaí Shopping Londrina)\n\n\nAcesso à Feira gratuito.";
    String imagemEvento     = "https://i2.wp.com/assentopublico.com.br/wp-content/uploads/2017/07/Feira-de-alimentos-org%C3%A2nicos-naturais-e-artesanais-ganha-um-novo-espa%C3%A7o-em-Ribeir%C3%A3o.jpg";
    Double longitudeEvento  = -51.2148497;
    Double latitudeEvento   = -30.037878;
    Double precoEvento      = 19d;
    String tituloEvento     = "Feira de Produtos Naturais e Orgânicos";
    int idEvento            = 4;

    EventosTest() {
    }


    @org.junit.jupiter.api.Test
    void getPessoaEvento() {

    }

    @org.junit.jupiter.api.Test
    void setPessoaEvento() {

    }

    @org.junit.jupiter.api.Test
    void getDataEvento() {
        Eventos eventos = new Eventos();
        eventos.setDataEvento(dataEvento);
        assertEquals(dataEvento, eventos.getDataEvento());
    }

    @org.junit.jupiter.api.Test
    void setDataEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getDescricaoEvento() {
        Eventos eventos = new Eventos();
        eventos.setDescricaoEvento(descricaoEvento);
        assertEquals(descricaoEvento, eventos.getDescricaoEvento());
    }

    @org.junit.jupiter.api.Test
    void setDescricaoEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getImagemEvento() {
        Eventos eventos = new Eventos();
        eventos.setImagemEvento(imagemEvento);
        assertEquals(imagemEvento, eventos.getImagemEvento());
    }

    @org.junit.jupiter.api.Test
    void setImagemEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getLongitudeEvento() {
        Eventos eventos = new Eventos();
        eventos.setLatitudeEvento(longitudeEvento);
        assertEquals(longitudeEvento, eventos.getLatitudeEvento());
    }

    @org.junit.jupiter.api.Test
    void setLongitudeEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getLatitudeEvento() {
        Eventos eventos = new Eventos();
        eventos.setLatitudeEvento(latitudeEvento);
        assertEquals(latitudeEvento, eventos.getLatitudeEvento());
    }

    @org.junit.jupiter.api.Test
    void setLatitudeEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getPrecoEvento() {
        Eventos eventos = new Eventos();
        eventos.setPrecoEvento(precoEvento);
        assertEquals(precoEvento, eventos.getPrecoEvento());
    }

    @org.junit.jupiter.api.Test
    void setPrecoEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getTituloEvento() {
        Eventos eventos = new Eventos();
        eventos.setTituloEvento(tituloEvento);
        assertEquals(tituloEvento, eventos.getTituloEvento());
    }

    @org.junit.jupiter.api.Test
    void setTituloEvento() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void getIdEvento() {
        Eventos eventos = new Eventos();
        eventos.setIdEvento(idEvento);
        assertEquals(idEvento, eventos.getIdEvento());
    }

    @org.junit.jupiter.api.Test
    void setIdEvento() {
        fail();
    }
}