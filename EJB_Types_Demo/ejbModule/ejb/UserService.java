package ejb;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Remote
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public interface UserService {
	public void setUsername(String username);

	public String getUsername();
}
