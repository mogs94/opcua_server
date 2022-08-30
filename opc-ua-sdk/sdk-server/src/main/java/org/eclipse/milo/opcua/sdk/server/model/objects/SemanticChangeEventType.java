package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.33">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.33</a>
 */
public interface SemanticChangeEventType extends BaseEventType {
    QualifiedProperty<SemanticChangeStructureDataType[]> CHANGES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Changes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=897"),
        1,
        SemanticChangeStructureDataType[].class
    );

    SemanticChangeStructureDataType[] getChanges();

    void setChanges(SemanticChangeStructureDataType[] value);

    PropertyType getChangesNode();
}