package jags.backend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Session")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Numero")
	private String numero;
	
	@Column(name="Type")
	private Integer type;
	
	@Column(name="Personnalisee")
	private Integer personalisee;
	
	@Column(name="Duree")
	private Integer duree;
	
	@Column(name="Validation")
	private Integer validation;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DateDebut")
	@DateTimeFormat(pattern = "dd-MM-yyyy:hh:mm:ss")
	private String dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DateFin")
	@DateTimeFormat(pattern = "dd-MM-yyyy:hh:mm:ss")
	private String dateFin;
	
	@OneToMany(mappedBy = "Session")
	private List<Assister> Prerequis;
	
}
