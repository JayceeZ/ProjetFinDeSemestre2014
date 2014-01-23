package model;
/**
 * Listes des états pour un appareil .
 * @author sualty
 *
 */
public enum Etat {
NEUF("Neuf"),
BIEN("Bien"),
MOYEN("Moyen"),
DEGRADE("Degrade");

private String description;
private Etat(String description) {
	this.description = description;
}

public String getDescription(){
	return this.description;
}

@Override
public String toString() {
	return this.description;
}
}
