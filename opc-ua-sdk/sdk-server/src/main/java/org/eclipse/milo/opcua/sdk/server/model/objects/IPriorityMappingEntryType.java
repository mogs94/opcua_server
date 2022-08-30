package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.15">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.15</a>
 */
public interface IPriorityMappingEntryType extends BaseInterfaceType {
    BaseDataVariableType getMappingUriNode();

    String getMappingUri();

    void setMappingUri(String value);

    BaseDataVariableType getPriorityLabelNode();

    String getPriorityLabel();

    void setPriorityLabel(String value);

    BaseDataVariableType getPriorityValue_PCPNode();

    UByte getPriorityValuePcp();

    void setPriorityValuePcp(UByte value);

    BaseDataVariableType getPriorityValue_DSCPNode();

    UInteger getPriorityValueDscp();

    void setPriorityValueDscp(UInteger value);
}