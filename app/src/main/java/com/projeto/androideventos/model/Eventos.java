package com.projeto.androideventos.model;


/**
 * OBJETIVO.......: MODEL DADOS RECEBIDOS
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
public class Eventos {

    String pessoaEvento;
    long dataEvento;
    String descricaoEvento;
    String imagemEvento;
    Double longitudeEvento;
    Double latitudeEvento;
    Double precoEvento;
    String tituloEvento;
    int idEvento;

    public String getPessoaEvento() {
        return pessoaEvento;
    }

    public void setPessoaEvento(String pessoaEvento) {
        this.pessoaEvento = pessoaEvento;
    }

    public long getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(long dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getImagemEvento() {
        return imagemEvento;
    }

    public void setImagemEvento(String imagemEvento) {
        this.imagemEvento = imagemEvento;
    }

    public Double getLongitudeEvento() {
        return longitudeEvento;
    }

    public void setLongitudeEvento(Double longitudeEvento) {
        this.longitudeEvento = longitudeEvento;
    }

    public Double getLatitudeEvento() {
        return latitudeEvento;
    }

    public void setLatitudeEvento(Double latitudeEvento) {
        this.latitudeEvento = latitudeEvento;
    }

    public Double getPrecoEvento() {
        return precoEvento;
    }

    public void setPrecoEvento(Double precoEvento) {
        this.precoEvento = precoEvento;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
}
