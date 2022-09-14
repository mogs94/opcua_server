/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.dtd;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary2;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.opcfoundation.opcua.binaryschema.TypeDictionary;

public abstract class DataTypeDictionaryInitializer {

    private static final String BINARY_DICTIONARY_URI = "http://opcfoundation.org/UA/";

    public void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        DataTypeDictionary2 binaryDictionary =
            dataTypeManager.getTypeDictionary(BINARY_DICTIONARY_URI);

        if (binaryDictionary == null) {
            try {
                TypeDictionary typeDictionary = BsdParser.parseBuiltinTypeDictionary();

                binaryDictionary = new BinaryDataTypeDictionary(typeDictionary);

                initializeStructs(namespaceTable, binaryDictionary);

                dataTypeManager.registerTypeDictionary(binaryDictionary);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract void initializeStructs(
        NamespaceTable namespaceTable,
        DataTypeDictionary2 binaryDictionary
    ) throws Exception;

}
