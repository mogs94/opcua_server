/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StandaloneSubscribedDataSetDataType extends SubscribedDataSetDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23600");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23852");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23920");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23988");

    private final String name;

    private final String[] dataSetFolder;

    private final DataSetMetaDataType dataSetMetaData;

    private final SubscribedDataSetDataType subscribedDataSet;

    public StandaloneSubscribedDataSetDataType(String name, String[] dataSetFolder,
                                               DataSetMetaDataType dataSetMetaData, SubscribedDataSetDataType subscribedDataSet) {
        this.name = name;
        this.dataSetFolder = dataSetFolder;
        this.dataSetMetaData = dataSetMetaData;
        this.subscribedDataSet = subscribedDataSet;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String getName() {
        return name;
    }

    public String[] getDataSetFolder() {
        return dataSetFolder;
    }

    public DataSetMetaDataType getDataSetMetaData() {
        return dataSetMetaData;
    }

    public SubscribedDataSetDataType getSubscribedDataSet() {
        return subscribedDataSet;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23852),
            new NodeId(0, 15630),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFolder", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMetaData", LocalizedText.NULL_VALUE, new NodeId(0, 14523), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscribedDataSet", LocalizedText.NULL_VALUE, new NodeId(0, 15630), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StandaloneSubscribedDataSetDataType> {
        @Override
        public Class<StandaloneSubscribedDataSetDataType> getType() {
            return StandaloneSubscribedDataSetDataType.class;
        }

        @Override
        public StandaloneSubscribedDataSetDataType decodeType(EncodingContext context,
                                                              UaDecoder decoder) {
            String name = decoder.decodeString("Name");
            String[] dataSetFolder = decoder.decodeStringArray("DataSetFolder");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.decodeStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            SubscribedDataSetDataType subscribedDataSet = (SubscribedDataSetDataType) decoder.decodeStruct("SubscribedDataSet", SubscribedDataSetDataType.TYPE_ID);
            return new StandaloneSubscribedDataSetDataType(name, dataSetFolder, dataSetMetaData, subscribedDataSet);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               StandaloneSubscribedDataSetDataType value) {
            encoder.encodeString("Name", value.getName());
            encoder.encodeStringArray("DataSetFolder", value.getDataSetFolder());
            encoder.encodeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.encodeStruct("SubscribedDataSet", value.getSubscribedDataSet(), SubscribedDataSetDataType.TYPE_ID);
        }
    }
}
