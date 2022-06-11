package start.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {
	// concat id and space
	private String id;
	private String space;
	private String idSpace;
	private String email;
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
 	private int amount;
    private String toAddress;
    private String fromAddress;
    private String hash;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	private String itemAttributes;

	public TransactionEntity() {

	}

	@Id
	public String getIdSpace() {
		return idSpace;
	}

	public void setIdSpace(String idSpace) {
		this.idSpace = idSpace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpace() {
		return space;
	}

	@Value("${spring.application.name:dummy}")
	public void setSpace(String space) {
		this.space = space;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


	@Lob
	public String getItemAttributes() {
		return itemAttributes;
	}

	public void setItemAttributes(String itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

}
