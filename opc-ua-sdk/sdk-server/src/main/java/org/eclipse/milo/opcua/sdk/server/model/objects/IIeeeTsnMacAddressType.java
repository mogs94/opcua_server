package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.13">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.13</a>
 */
public interface IIeeeTsnMacAddressType extends BaseInterfaceType {
    BaseDataVariableType getDestinationAddressNode();

    UByte[] getDestinationAddress();

    void setDestinationAddress(UByte[] value);

    BaseDataVariableType getSourceAddressNode();

    UByte[] getSourceAddress();

    void setSourceAddress(UByte[] value);
}