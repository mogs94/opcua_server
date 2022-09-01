package org.eclipse.milo.opcua.sdk.client.model.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part16/4.6.5">https://reference.opcfoundation.org/v105/Core/docs/Part16/4.6.5</a>
 */
public interface ExpressionGuardVariableType extends GuardVariableType {
    QualifiedProperty<ContentFilter> EXPRESSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Expression",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=586"),
        -1,
        ContentFilter.class
    );

    /**
     * Get the local value of the Expression Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Expression Node.
     * @throws UaException if an error occurs creating or getting the Expression Node.
     */
    ContentFilter getExpression() throws UaException;

    /**
     * Set the local value of the Expression Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the Expression Node.
     * @throws UaException if an error occurs creating or getting the Expression Node.
     */
    void setExpression(ContentFilter value) throws UaException;

    /**
     * Read the value of the Expression Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link ContentFilter} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    ContentFilter readExpression() throws UaException;

    /**
     * Write a new value for the Expression Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link ContentFilter} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeExpression(ContentFilter value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readExpression}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends ContentFilter> readExpressionAsync();

    /**
     * An asynchronous implementation of {@link #writeExpression}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeExpressionAsync(ContentFilter value);

    /**
     * Get the Expression {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Expression {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getExpressionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getExpressionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getExpressionNodeAsync();
}