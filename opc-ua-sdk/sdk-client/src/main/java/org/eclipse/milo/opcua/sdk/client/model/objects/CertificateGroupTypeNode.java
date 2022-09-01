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

public class CertificateGroupTypeNode extends BaseObjectTypeNode implements CertificateGroupType {
    public CertificateGroupTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                    RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                    UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public NodeId[] getCertificateTypes() throws UaException {
        PropertyTypeNode node = getCertificateTypesNode();
        return (NodeId[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setCertificateTypes(NodeId[] value) throws UaException {
        PropertyTypeNode node = getCertificateTypesNode();
        node.setValue(new Variant(value));
    }

    @Override
    public NodeId[] readCertificateTypes() throws UaException {
        try {
            return readCertificateTypesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCertificateTypes(NodeId[] value) throws UaException {
        try {
            writeCertificateTypesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId[]> readCertificateTypesAsync() {
        return getCertificateTypesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (NodeId[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCertificateTypesAsync(NodeId[] certificateTypes) {
        DataValue value = DataValue.valueOnly(new Variant(certificateTypes));
        return getCertificateTypesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getCertificateTypesNode() throws UaException {
        try {
            return getCertificateTypesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getCertificateTypesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "CertificateTypes",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public TrustListTypeNode getTrustListNode() throws UaException {
        try {
            return getTrustListNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends TrustListTypeNode> getTrustListNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "TrustList",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (TrustListTypeNode) node);
    }

    @Override
    public CertificateExpirationAlarmTypeNode getCertificateExpiredNode() throws UaException {
        try {
            return getCertificateExpiredNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends CertificateExpirationAlarmTypeNode> getCertificateExpiredNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "CertificateExpired",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (CertificateExpirationAlarmTypeNode) node);
    }

    @Override
    public TrustListOutOfDateAlarmTypeNode getTrustListOutOfDateNode() throws UaException {
        try {
            return getTrustListOutOfDateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends TrustListOutOfDateAlarmTypeNode> getTrustListOutOfDateNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "TrustListOutOfDate",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (TrustListOutOfDateAlarmTypeNode) node);
    }
}