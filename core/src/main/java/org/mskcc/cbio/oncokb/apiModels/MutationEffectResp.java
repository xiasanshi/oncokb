package org.mskcc.cbio.oncokb.apiModels;

/**
 * Created by Hongxin Zhang on 2/21/18.
 */
public class MutationEffectResp {
    String knownEffect = "";
    String description = "";
    Citations citations;

    public String getKnownEffect() {
        return knownEffect;
    }

    public void setKnownEffect(String knownEffect) {
        this.knownEffect = knownEffect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Citations getCitations() {
        return citations;
    }

    public void setCitations(Citations citations) {
        this.citations = citations;
    }
}
