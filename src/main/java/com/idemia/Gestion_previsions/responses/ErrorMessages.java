package com.idemia.Gestion_previsions.responses;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Champ obligatoire manquant"),
	RECORD_ALREADY_EXISTS("l'enregistrement existe déjà"),
	INTERNAL_SERVER_ERROR("Erreur interne du serveur"),
	NO_RECORD_FOUND("L'enregistrement avec l'identifiant fourni est introuvable");
	
	
	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
