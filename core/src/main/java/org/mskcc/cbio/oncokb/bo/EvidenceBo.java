/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.cbio.oncokb.bo;

import java.util.Collection;
import java.util.List;
import org.mskcc.cbio.oncokb.model.Alteration;
import org.mskcc.cbio.oncokb.model.Evidence;
import org.mskcc.cbio.oncokb.model.EvidenceType;
import org.mskcc.cbio.oncokb.model.Gene;
import org.mskcc.cbio.oncokb.model.TumorType;

/**
 *
 * @author jgao
 */
public interface EvidenceBo extends GenericBo<Evidence> {
    /**
     * Find Evidences by alteration ID
     * @param alterations
     * @return 
     */
    List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations);
    
    /**
     * Find Evidences by alteration ID and evidence type
     * @param alterations
     * @param evidenceType
     * @return 
     */
    List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations, EvidenceType evidenceType);
    
    /**
     * 
     * @param alterations
     * @param evidenceType
     * @param tumorType
     * @return 
     */
    List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations, EvidenceType evidenceType, TumorType tumorType);
    
    /**
     * Find Evidences by Entrez Gene ID
     * @param genes
     * @return 
     */
    List<Evidence> findEvidencesByGene(Gene gene);
    
    /**
     * 
     * @param gene
     * @param evidenceType
     * @return 
     */
    List<Evidence> findEvidencesByGene(Gene gene, EvidenceType evidenceType);
}