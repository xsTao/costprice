package nc.login.identify.ui;

import nc.identityverify.itf.AbstractAfterVerifySuccessClient;

public class StaticPWDVerifySuccessClient extends AbstractAfterVerifySuccessClient {

    @Override
    public boolean doVerifySuccess(Object obj) {
        return true;
    }
}
