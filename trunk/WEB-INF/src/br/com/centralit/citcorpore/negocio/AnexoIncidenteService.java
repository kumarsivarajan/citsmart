/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.centralit.citcorpore.bean.AnexoIncidenteDTO;
import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.citframework.service.CrudServiceEjb2;

/**
 * 
 * @author breno.guimaraes
 */
public interface AnexoIncidenteService extends CrudServiceEjb2 {
    void create(Collection<UploadDTO> arquivosUpados, Integer idIncidente) throws Exception;
    Collection<AnexoIncidenteDTO> consultarAnexosIncidentes(Integer idIncidente)  throws Exception;
}
