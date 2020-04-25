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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.AbstractConversionTest.ConversionType.EXPLICIT;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.AbstractConversionTest.ConversionType.NONE;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class DoubleConversionsTest extends AbstractConversionTest<Double> {

    @Override
    protected Class<Double> getSourceClass() {
        return Double.class;
    }

    @Override
    public Conversion[] getConversions(BuiltinDataType targetType) {
        switch (targetType) {
            case Boolean: {
                return new Conversion[]{
                    c(0.0, false),
                    c(0.1, true),
                    c(1.0, true)
                };
            }

            case Byte: {
                return new Conversion[]{
                    c(0.0, ubyte(0)),
                    c(UByte.MIN.doubleValue(), UByte.MIN),
                    c(UByte.MAX.doubleValue(), UByte.MAX),
                    f(UByte.MIN.doubleValue() - 1, targetType, ConversionUnderflowException.class),
                    f(UByte.MAX.doubleValue() + 1, targetType, ConversionOverflowException.class)
                };
            }

            case Float: {
                return new Conversion[]{
                    c(0.0, 0.0f),
                    c((double) Float.MIN_VALUE, Float.MIN_VALUE),
                    c((double) Float.MAX_VALUE, Float.MAX_VALUE),
                    c((double) -Float.MIN_VALUE, -Float.MIN_VALUE),
                    c((double) -Float.MAX_VALUE, -Float.MAX_VALUE),
                    f(-Double.MAX_VALUE, targetType, ConversionUnderflowException.class),
                    f(Double.MAX_VALUE, targetType, ConversionOverflowException.class)
                };
            }

            case Int16: {
                return new Conversion[]{
                    c(0.0, (short) 0),
                    c((double) Short.MIN_VALUE, Short.MIN_VALUE),
                    c((double) Short.MAX_VALUE, Short.MAX_VALUE),
                    f(Short.MIN_VALUE - 1.0, targetType, ConversionUnderflowException.class),
                    f(Short.MAX_VALUE + 1.0, targetType, ConversionOverflowException.class)
                };
            }

            case Int32: {
                return new Conversion[]{
                    c(0.0, 0),
                    c((double) Integer.MIN_VALUE, Integer.MIN_VALUE),
                    c((double) Integer.MAX_VALUE, Integer.MAX_VALUE),
                    f(Integer.MIN_VALUE - 1.0, targetType, ConversionUnderflowException.class),
                    f(Integer.MAX_VALUE + 1.0, targetType, ConversionOverflowException.class)
                };
            }

            case Int64: {
                return new Conversion[]{
                    c(0.0, 0L),
                    c(Long.MIN_VALUE + 0.0, Long.MIN_VALUE),
                    c(Long.MAX_VALUE + 0.0, Long.MAX_VALUE),
                    f(-Double.MAX_VALUE, targetType, ConversionUnderflowException.class),
                    f(Double.MAX_VALUE, targetType, ConversionOverflowException.class)
                };
            }

            case SByte: {
                return new Conversion[]{
                    c(0.0, (byte) 0),
                    c(Byte.MIN_VALUE + 0.0, Byte.MIN_VALUE),
                    c(Byte.MAX_VALUE + 0.0, Byte.MAX_VALUE),
                    f(Byte.MIN_VALUE - 1.0, targetType, ConversionUnderflowException.class),
                    f(Byte.MAX_VALUE + 1.0, targetType, ConversionOverflowException.class)
                };
            }

            case String: {
                return new Conversion[]{
                    c(0.0, "0.0"),
                    c(1.5, "1.5")
                };
            }

            case UInt16: {
                return new Conversion[]{
                    c(0.0, ushort(0)),
                    c(UShort.MAX_VALUE + 0.0, UShort.MAX),
                    f(UShort.MIN_VALUE - 1.0, targetType, ConversionUnderflowException.class),
                    f(UShort.MAX_VALUE + 1.0, targetType, ConversionOverflowException.class)
                };
            }

            case UInt32: {
                return new Conversion[]{
                    c(0.0, uint(0)),
                    c(UInteger.MAX_VALUE + 0.0, UInteger.MAX),
                    f(UInteger.MIN_VALUE - 1.0, targetType, ConversionUnderflowException.class),
                    f(UInteger.MAX_VALUE + 1.0, targetType, ConversionOverflowException.class)
                };
            }

            case UInt64: {
                return new Conversion[]{
                    c(0.0, ulong(0)),
                    c(Double.MAX_VALUE, ulong(Math.round(Double.MAX_VALUE))),
                    f(-1.0, targetType, ConversionUnderflowException.class)
                };
            }

            default:
                return new ConversionSuccess[0];
        }
    }

    @Override
    public ConversionType getConversionType(BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:       return EXPLICIT;
            case Byte:          return EXPLICIT;
            case Float:         return EXPLICIT;
            case Int16:         return EXPLICIT;
            case Int32:         return EXPLICIT;
            case Int64:         return EXPLICIT;
            case SByte:         return EXPLICIT;
            case String:        return EXPLICIT;
            case UInt16:        return EXPLICIT;
            case UInt32:        return EXPLICIT;
            case UInt64:        return EXPLICIT;
            default:            return NONE;
        }
        //@formatter:on
    }

    @Override
    protected Object convert(
        Object fromValue,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionUnderflowException, ConversionOverflowException {

        return DoubleConversions.convert(fromValue, targetType, implicit);
    }

}
