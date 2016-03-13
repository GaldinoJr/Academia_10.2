package com.example.galdino.academia_102.Dominio;


import com.example.galdino.academia_102.R;

public class Exercicio extends EntidadeDominio {
	private String nome;
	private String descricao;
	private String nomeLogico;
	private Integer idGrupo;
	private int idImage;
	private String[] vetExercicios;
	private Integer[] VetImgId;

	private Integer[] imgIdAbdomen = {
			R.drawable.abdominal_giro_com_pernas_elevadas,
			R.drawable.abdominal_rotacao_de_tronco_com_barra_reta,
			R.drawable.abdominal_obliquo_declinado,
			R.drawable.abdominal_em_roda,
			R.drawable.abdominal_elevacao_de_pernas_na_barra_fixa,
			R.drawable.abdominal_elevacao_de_pernas_inclinado,
			R.drawable.abdominal_reto_superior_com_bola,
			R.drawable.abdominal_flexao_lateral_com_bola,
			R.drawable.abdominal_inferior_com_elevacao_de_bola,
			R.drawable.abdominal_flexao_e_extensao_de_quadril_na_bola,
			R.drawable.abdominal_obliquo_na_paralela,
			R.drawable.abdominal_inferior_com_elevacao_de_quadril,
			R.drawable.abdominal_com_giro,
			R.drawable.abdominal_elevacao_de_joelhos,
			R.drawable.abdominal_elevacao_de_joelhos_declinado,
			R.drawable.abdominal_elevacao_de_pernas,
			R.drawable.abdominal_elevacao_de_pernas_com_peso,
			R.drawable.abdominal_elevacao_de_pernas_na_vertical,
			R.drawable.abdominal_grupado,
			R.drawable.abdominal_grupado_invertido,
			R.drawable.abdominal_grupado_invertido_declinado,
			R.drawable.abdominal_grupado_no_aparelho,
			R.drawable.abdominal_inclinacao_lateral,
			R.drawable.abdominal_tradicional
	};
	private Integer[] imgIdBiceps = {
		R.drawable.biceps_rosca_direta_com_barra_w,
		R.drawable.biceps_rosca_direta_com_barra_reta,
		R.drawable.biceps_rosca_concentrada,
		R.drawable.biceps_rosca_direta_com_halter_deitado,
		R.drawable.biceps_rosca_direta_no_cabo,
		R.drawable.biceps_rosca_direta_no_cabo_unilateral,
		R.drawable.biceps_rosca_martelo,
		R.drawable.biceps_rosca_scott_maquina,
		R.drawable.biceps_rosca_scott_na_polia,
		R.drawable.biceps_rosca_spider_com_barra_reta,
		R.drawable.biceps_rosca_spider_com_barra_w
	};
	private Integer[] imgIdCosta = {
		R.drawable.costas_bom_dia,
		R.drawable.costas_levantamento_terra,
		R.drawable.costas_remada_baixa,
		R.drawable.costas_triangulo,
		R.drawable.costas_flexao_na_barra_maos_supinadas,
		R.drawable.costas_pulley_frente,
		R.drawable.costas_pulley_tras,
		R.drawable.costas_remada_inclinada_com_halteres,
		R.drawable.costas_flexao_na_barra,
		R.drawable.costas_remada_unilateral_com_halter,
		R.drawable.costas_puxada_alta_com_bracos_estendidos,
		R.drawable.costas_extensao_lombar,
		R.drawable.costas_pulley_fechado,
		R.drawable.costas_remada_horizontal_maquina_pegada_pronada,
		R.drawable.costas_remada_em_pronacao,
		R.drawable.costas_levantamento_terra_com_halter,
		R.drawable.costas_remada_curvada_com_halteres,
		R.drawable.costas_remada_cavalinho,
		R.drawable.costas_remada_inclinada_com_barra
		
	};
	private Integer[] imgIdCoxa = {
		R.drawable.coxa_stiff_com_halteres,
		R.drawable.coxa_stiff,
		R.drawable.coxa_cadeira_adutora,
		R.drawable.coxa_cadeira_extensora,
		R.drawable.coxa_cadeira_flexora,
		R.drawable.coxa_leg_press_45,
		R.drawable.coxa_leg_press_45_unilateral,
		R.drawable.coxa_agachamento_livre,
		R.drawable.coxa_afundo,
		R.drawable.coxa_afundo_com_barra,
		R.drawable.coxa_agachamento_frontal_com_barra,
		R.drawable.coxa_levatamento_terra_sumo_com_halter,
		R.drawable.coxa_cadeira_extensora_unilateral,
		R.drawable.coxa_adutor_na_maquina,
		R.drawable.coxa_hack_horizontal,
		R.drawable.coxa_avanco,
		R.drawable.coxa_agachamento_na_bola,
		R.drawable.coxa_agachamento_na_bola_com_halteres,
		R.drawable.coxa_agachamento_livre_estilo_hack,
		R.drawable.coxa_agachamento_livre_estilo_hack_com_calcanhares_elevados,
		R.drawable.coxa_agachamento_no_smith,
		R.drawable.coxa_adutor_na_polia_baixa
	};
	private Integer[] imgIdGluteo = {
		R.drawable.gluteo_mesa_flexora,
		R.drawable.gluteo_abducao_de_quaril_deitado,
		R.drawable.gluteo_step_up_com_barra,
		R.drawable.gluteo_extensao_de_quadril_no_banco_com_pernas_esticadas,
		R.drawable.gluteo_extensao_de_quadril_na_polia_baixa,
		R.drawable.gluteo_elevacao_de_quadril
	};
	private Integer[] imgIdOmbro = {
		R.drawable.ombro_desenvolvimento_frente,
		R.drawable.ombro_remada_alta,
		R.drawable.ombro_rotacao_externa, 
		R.drawable.ombro_rotacao_interna,
		R.drawable.ombro_desenvolvimento_maquina_pegada_pronada,
		R.drawable.ombro_desenvolvimento_posterior,
		R.drawable.ombro_desenvolvimento_com_halter,
		R.drawable.ombro_desenvolvimento_arnold,
		R.drawable.ombro_elevacao_frente_com_halter_pegada_variavel,
		R.drawable.ombro_elevacao_lateral,
		R.drawable.ombro_elevacao_frontal_unilateral_pegada_variavel,
		R.drawable.ombro_elevacao_lateral_cross,
		R.drawable.ombro_remada_alta_cross,
		R.drawable.ombro_elevacao_lateral_unilateral_com_halter,
		R.drawable.ombro_elevacao_lateral_sentado,
		R.drawable.ombro_elevacao_lateral_sentado_e_inclinado,
		R.drawable.ombro_remada_alta_com_halter,
		R.drawable.ombro_crucifixo_inverso_com_halter,
		R.drawable.ombro_crucifixo_inverso_na_maquina
	};
	private Integer[] imgIdPanturrilha = {
			R.drawable.panturrilha_pant_sentado_com_barra
	};

	private Integer[] imgidPeito = {
			R.drawable.peito_pullover_quebrado_com_barra,
			R.drawable.peito_crucifixo_reto_com_halter,
			R.drawable.peito_crucifixo_declinado_com_halter,
			R.drawable.peito_supino_inclinado_com_halter,
			R.drawable.peito_supino_reto_maquina,
			R.drawable.peito_supino_declinado_com_halter,
			R.drawable.peito_supino_reto_com_halter,
			R.drawable.peito_flexao_de_braco,
			R.drawable.peito_peck_deck,
			R.drawable.peito_pullover_com_barra, 
			R.drawable.peito_pullover_com_halter,
			R.drawable.peito_pullover_com_halter_tipo_2,
			R.drawable.peito_pullover_quebrado_com_halter,
			R.drawable.peito_supino_maquina_sentado,
			R.drawable.peito_crossover,
			R.drawable.peito_supino_reto_com_barra,
			R.drawable.peito_supino_inclinado_com_barra,
			R.drawable.peito_supino_declinado_com_barra,
			R.drawable.peito_paralela,
			R.drawable.peito_crucifixo_inclinado_com_halter
	};

	private Integer[] imgIdTriceps = {
			R.drawable.triceps_paralela,
			R.drawable.triceps_testa_com_halteres_tipo_2,
			R.drawable.triceps_testa_inclinado_com_barra,
			R.drawable.triceps_testa_com_barra_w,
			R.drawable.triceps_extensao_com_barra_reta,
			R.drawable.triceps_copa_com_halter,
			R.drawable.triceps_patada_com_halter,
			R.drawable.triceps_patada_com_halter_sentado,
			R.drawable.triceps_extensao_com_barra_w,
			R.drawable.triceps_testa_com_barra_reta,
			R.drawable.triceps_supino_pegada_fechada,
			R.drawable.triceps_cross,
			R.drawable.triceps_frances,
			R.drawable.triceps_cross_unilateral,
			R.drawable.triceps_testa_com_halteres,
			R.drawable.triceps_supino_pegada_invertida,
			R.drawable.triceps_testa_na_polia_baixa,
			R.drawable.triceps_corda_com_polia_baixa,
			R.drawable.triceps_extensao_na_horizontal_com_barra,
			R.drawable.triceps_supino_em_pronacao_com_halteres
	};


	private String[] vetExAbdomen = {"Inclinacao lateral", "Giro com pernas elevadas", "Rotacao de tronco com barra reta" , "Obliquo declinado", "Em roda",
		"Elevacao de pernas na barra fixa" , "Reto superior com bola" , "Flexao lateral com bola" , "Inferior com elevacao de bola" ,
		"Flexao e extensao de quadril na bola" , "Obliquo na paralela", "Inferior com elevacao de quadril" , "Com giro", "Elevacao de joelhos", 
		"Elevacao de joelhos declinado", "Elevacao de pernas" , "Elevacao de pernas com peso", "Elevacao de pernas inclinado", "Elevacao de pernas na vertical", "Grupado", 
		"Grupado invertido", "Grupado invertido declinado", "Grupado no aparelho", "Tradicional"};
	private  String[] vetExBiceps = {"Rosca direta com barra reta", "Rosca spider com barra reta", "Rosca direta no cabo", "Rosca spider com barra w",
			"Rosca scott maquina","Rosca martelo", "Rosca scott na polia", "Rosca concentrada", "Rosca direta com barra w", "Rosca direta no cabo unilateral",
			"Rosca direta com halter deitado"};
	private  String[] vetExPeito = {"Pullover quebrado com barra", "Crucifixo reto com halter", "Crucifixo declinado com halter", "Supino inclinado com halter",
			"Supino declinado com halter", "Supino reto com halter", "Supino reto maquina", "Flexao de braco", "Peck deck", "Pullover com barra", "Pullover com halter",
			"Pullover com halter tipo 2", "Pullover quebrado com halter", "Supino maquina sentado",	"Supino reto com barra", "Supino inclinado com barra",
			"Supino declinado com barra","Crossover","Paralela","Crucifixo inclinado com halter"};
	private  String[] vetExCosta = { "Levantamento terra", "Bom dia", "Triangulo", "Remada baixa", "Remada cavalinho", "Flexao na barra maos supinadas", "Pulley frente", "Pulley tras",
			"Remada inclinada com halteres", "Flexao na barra maos supinadas", "Remada unilateral com halter", "Puxada alta com bracos estendidos", 
			"Extensao lombar", "Pulley fechado", "Remada horizontal maquina pegada pronada", "Remada em pronacao",  
			"Levantamento terra com halter", "Remada curvada com halteres", "Remada inclinada com barra"};
	private  String[] vetExOmbro = {"Remada alta", "Desenvolvimento frente", "Rotacao externa",	"Rotacao interna", "Desenvolvimento maquina pegada pronada",
			"Desenvolvimento posterior", "Desenvolvimento com halter", "Desenvolvimento Arnold", "Elevacao frente com halter pegada variavel", 
			"Elevacao frontal unilateral pegada variavel", "Elevacao lateral", "Elevacao lateral cross", "Remada alta cross", "Elevacao lateral unilateral com halter",
			"Elevacao lateral sentado", "Elevacao lateral sentado e inclinado", "Remada alta com halter", "Crucifixo inverso com halter", "Crucifixo inverso na maquina"};
	private  String[] vetExTriceps = {"Paralela", "Testa com halteres tipo 2", "Testa inclinado com barra", "Testa com barra w", "Copa com halter", "Extensao com barra reta",
			"Extensao com barra w", "Patada com halter", "Patada com halter sentado", "Testa com barra reta", "Supino pegada fechada", "Cross", "Frances",
			"Cross unilateral", "Testa com halteres", "Supino pegada invertida", "Testa na polia baixa", "Corda com polia baixa", 
			"Extensao na horizontal com barra", "Supino em pronacao com halteres"};
	private  String[] vetExCoxa = {"Cadeira flexora", "Adutor na polia baixa", "Cadeira extensora", "Cadeira adutora", "Leg Press 45", "Leg press 45 unilateral", "Agachamento livre", "Afundo",
			"Afundo com barra",	"Agachamento frontal com barra", "Levatamento terra sumo com halter", "Cadeira extensora unilateral", "Adutor na maquina",
			"Hack horizontal", "Avanco", "Agachamento na bola", "Agachamento na bola com halteres", "Agachamento livre estilo hack", 
			"Agachamento livre estilo hack com calcanhares elevados", "Agachamento no smith", "Stiff", "Stiff com halteres"};
	private String[] vetExPanturrilha = {"Pant sentado com barra"};
	private String[] vetExGluteo = {"Mesa flexora", "Elevacao De Quadril", "Abducao de quaril deitado", "Step up com barra",
			"Extensao de quadril no banco com pernas esticadas", "Extensao de quadril na polia baixa"};

	// gets
	public String getNome() {
		return nome;
	}
	public int getIdImage() {
		return idImage;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getNomeLogico() {
		return nomeLogico;
	}
	public Integer[] getVetId()
	{
		return VetImgId;
	}
	public String[] getVetCorrespondente()
	{
		return vetExercicios;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}

	// sets
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setNomeLogico(String nomeLogico) {
		this.nomeLogico = nomeLogico;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}


	private  String[] orderAlfabeticamenteVetor(String[] vetor)
	{
		int nTamanho,
			i,
			comp;
		String sAux;
		Boolean flgHouveTroca = false;
		nTamanho = (vetor.length)-1;
		do
		{
			flgHouveTroca = false;
			for(i = 0; i < nTamanho; i++)
			{
				comp = vetor[i].compareTo(vetor[i+1]); // compara o indice atual com o próximo indice
				if(comp > 0) // Indice atual é maior que o próximo indice?
				{ // Sim, então troca
					sAux = vetor[i];
					vetor[i] = vetor[i+1];
					vetor[i+1] = sAux;
					i = -1; // recomeça do zero
					flgHouveTroca = true; // indica que houve troca
				}
			}
		}while(flgHouveTroca);	
		return vetor;
	}
	/*
	 * public String[] ordenarAlfabeticamenteVetor(String[] vetor)
	{	
		int i,
			nQtdeRegistros,
			comp;
		String sAux;
		
		nQtdeRegistros = (vetor.length) - 1;
		
		for(i = 0; i < nQtdeRegistros; i++) // Passa por todos os registros do vetor
		{
			comp = vetor[i].compareTo(vetor[i+1]); // compara o indice atual com o próximo indice
			if(comp > 0) // Indice atual é maior que o próximo indice?
			{ // Sim, então troca
				sAux = vetor[i];
				vetor[i] = vetor[i+1];
				vetor[i+1] = sAux;
				i = -1; // recomça do zero
			}
		}
		return vetor;
	}
	*/

	public void ordenarVetores(String grupo)
	{
		if(grupo.equals("Peito"))  // Peito?
    	{
    		vetExercicios = vetExPeito;
    		VetImgId = imgidPeito;
    	}
    	else if(grupo.equals("Costas")) // Costa?
    	{
    		vetExercicios = vetExCosta;
    		VetImgId = imgIdCosta;
    	}
    	else if(grupo.equals("Ombro"))  // Ombro
    	{
    		vetExercicios = vetExOmbro;
    		VetImgId = imgIdOmbro;
    	}
    	else if(grupo.equals("Biceps")) // Biceps?
    	{
    		vetExercicios = vetExBiceps;
    		VetImgId = imgIdBiceps;
    	}
    	else if(grupo.equals("Triceps")) // Triceps?
    	{
    		vetExercicios = vetExTriceps;
    		VetImgId = imgIdTriceps;
    	}
    	else if(grupo.equals("Coxa")) // Perna?
    	{
    		vetExercicios = vetExCoxa;
    		VetImgId = imgIdCoxa;
    	}
    	else if(grupo.equals("Panturrilha"))
    	{
    		vetExercicios = vetExPanturrilha;
    		VetImgId = imgIdPanturrilha;
    	}
    	else if(grupo.equals("Abdomen")) // Abdomen?
    	{
    		vetExercicios = vetExAbdomen;
    		VetImgId = imgIdAbdomen;
    	}
    	else if(grupo.equals("Gluteo"))
    	{
    		vetExercicios = vetExGluteo;
    		VetImgId = imgIdGluteo;
    	}
    	else
    	{
    		vetExercicios = null;
    	}
		if(vetExercicios != null)
			vetExercicios = orderAlfabeticamenteVetor(vetExercicios);
	}
}
