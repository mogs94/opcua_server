package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class AuditConditionShelvingEventTypeNode extends AuditConditionEventTypeNode implements AuditConditionShelvingEventType {
    public AuditConditionShelvingEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                               RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                               UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public Double getShelvingTime() throws UaException {
        PropertyTypeNode node = getShelvingTimeNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setShelvingTime(Double value) throws UaException {
        PropertyTypeNode node = getShelvingTimeNode();
        node.setValue(new Variant(value));
    }

    @Override
    public Double readShelvingTime() throws UaException {
        try {
            return readShelvingTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeShelvingTime(Double value) throws UaException {
        try {
            writeShelvingTimeAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readShelvingTimeAsync() {
        return getShelvingTimeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeShelvingTimeAsync(Double shelvingTime) {
        DataValue value = DataValue.valueOnly(new Variant(shelvingTime));
        return getShelvingTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getShelvingTimeNode() throws UaException {
        try {
            return getShelvingTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getShelvingTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "ShelvingTime",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}