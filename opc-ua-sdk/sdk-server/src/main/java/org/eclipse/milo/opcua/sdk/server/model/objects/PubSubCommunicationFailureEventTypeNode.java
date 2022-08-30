package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PubSubCommunicationFailureEventTypeNode extends PubSubStatusEventTypeNode implements PubSubCommunicationFailureEventType {
    public PubSubCommunicationFailureEventTypeNode(UaNodeContext context, NodeId nodeId,
                                                   QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                   UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                   RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                                   UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public PubSubCommunicationFailureEventTypeNode(UaNodeContext context, NodeId nodeId,
                                                   QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                   UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                   RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public PropertyTypeNode getErrorNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(PubSubCommunicationFailureEventType.ERROR);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public StatusCode getError() {
        return getProperty(PubSubCommunicationFailureEventType.ERROR).orElse(null);
    }

    @Override
    public void setError(StatusCode value) {
        setProperty(PubSubCommunicationFailureEventType.ERROR, value);
    }
}