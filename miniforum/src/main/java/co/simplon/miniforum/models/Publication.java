package co.simplon.miniforum.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Publication {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name = "TEXTE", nullable = false, length=2047)
	private String textContent;
	
	private Date datePublication;
	
	@ManyToOne
	private ForumUser author;

	
	// CONSTRUCTORS
	
	public Publication() {
	}
	public Publication(String textContent, ForumUser author, Date datePublication) {
		this.textContent = textContent;
		this.author = author;
		this.datePublication = datePublication;
	}
	public Publication(int id, String textContent, Date datePublication, ForumUser author) {
		this.id = id;
		this.textContent = textContent;
		this.datePublication = datePublication;
		this.author = author;
	}

	// IMPORTANT OVERRIDES

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((datePublication == null) ? 0 : datePublication.hashCode());
		result = prime * result + id;
		result = prime * result + ((textContent == null) ? 0 : textContent.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (datePublication == null) {
			if (other.datePublication != null)
				return false;
		} else if (!datePublication.equals(other.datePublication))
			return false;
		if (id != other.id)
			return false;
		if (textContent == null) {
			if (other.textContent != null)
				return false;
		} else if (!textContent.equals(other.textContent))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Publication [id=" + id + ", textContent=" + textContent + ", datePublication=" + datePublication
				+ ", author=" + author + "]";
	}
	
	// GETTERS / SETTERS
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public ForumUser getAuthor() {
		return author;
	}
	public void setAuthor(ForumUser author) {
		this.author = author;
	}
	public Date getDatePublication() {
		return datePublication;
	}
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}
	
}
