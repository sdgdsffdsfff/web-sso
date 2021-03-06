package com.github.ebnew.ki4so.core.authentication.resolvers;

import com.github.ebnew.ki4so.core.authentication.DefaultKnightUser;
import com.github.ebnew.ki4so.core.authentication.KnightCredential;
import com.github.ebnew.ki4so.core.authentication.KnightNamePasswordCredential;
import com.github.ebnew.ki4so.core.authentication.KnightUser;

public class UsernamePasswordCredentialToPrincipalResolver implements CredentialToPrincipalResolver{
	
	/** Default class to support if one is not supplied. */
	private static final Class<KnightNamePasswordCredential> DEFAULT_CLASS = KnightNamePasswordCredential.class;

	/** Class that this instance will support. */
	private Class<?> classToSupport = DEFAULT_CLASS;
	
	/**
	 * Boolean to determine whether to support subclasses of the class to
	 * support.
	 */
	private boolean supportSubClasses = true;

	public void setSupportSubClasses(boolean supportSubClasses) {
		this.supportSubClasses = supportSubClasses;
	}

	@Override
	public KnightUser resolvePrincipal(KnightCredential credential) {
		//若类型匹配，则进行转换。
		if(credential!=null && this.supports(credential)){
            KnightNamePasswordCredential usernamePasswordCredential = (KnightNamePasswordCredential)credential;
            DefaultKnightUser principal = new DefaultKnightUser();
			//设置用户名为唯一标识。
			principal.setId(usernamePasswordCredential.getUsername());
			//设置参数表为用户属性。
			principal.setAttributes(usernamePasswordCredential.getParameters());
			return principal;
		}
		return null;
	}

	@Override
	public boolean supports(KnightCredential credential) {
		return credential != null
		&& (this.classToSupport.equals(credential.getClass()) || (this.classToSupport
				.isAssignableFrom(credential.getClass()))
				&& this.supportSubClasses);
	}

}
