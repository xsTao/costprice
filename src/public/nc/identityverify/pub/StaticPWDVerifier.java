package nc.identityverify.pub;

import nc.identityverify.itf.AbstractIdentityVerifier;
import nc.identityverify.vo.AuthenSubject;
import nc.login.vo.ILoginConstants;
import nc.vo.sm.UserVO;

public class StaticPWDVerifier extends AbstractIdentityVerifier {

    @Override
    public int verify(AuthenSubject subject, UserVO user) throws Exception {
        return ILoginConstants.USER_IDENTITY_LEGAL;
    }

}
