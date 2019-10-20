package ca.ulaval.glo4002.booking.domain;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateFormatException;

public class OrderDate {

	private LocalDateTime value;

	/*
	 * TODO Vérifier si on devrait pas plutôt mettre la constante direct ici
	 * 
	 * voir commentaire sur notre code dans le rewiew : Pourquoi ne pas mettre les
	 * messages d'erreur directement dans les exceptions ? Vous avez déjà beaucoup
	 * de constante et vous avez seulement 3 story. Vous allez faire quoi quand vous
	 * aurez 100 story de fait ?
	 */
	public OrderDate(String value) {
		try {
			this.value = ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
		} catch (Exception exception) {
			throw new InvalidOrderDateFormatException();
		}
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
