/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;

public class ConversionFailedException extends ConversionException {

    public ConversionFailedException(BuiltinDataType sourceType, BuiltinDataType targetType) {
        super(String.format("conversion from %s to %s failed", sourceType, targetType));
    }

    public ConversionFailedException(BuiltinDataType sourceType, BuiltinDataType targetType, Throwable cause) {
        super(String.format("conversion from %s to %s failed", sourceType, targetType), cause);
    }

}
