// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transaction.proto

package id.grocery.tunas.grpc;

/**
 * Protobuf type {@code proto.TransactionBody}
 */
public  final class TransactionBody extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:proto.TransactionBody)
    TransactionBodyOrBuilder {
  // Use TransactionBody.newBuilder() to construct.
  private TransactionBody(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransactionBody() {
    userId_ = "";
    products_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TransactionBody(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            userId_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              products_ = new java.util.ArrayList<id.grocery.tunas.grpc.TransactionProduct>();
              mutable_bitField0_ |= 0x00000002;
            }
            products_.add(
                input.readMessage(id.grocery.tunas.grpc.TransactionProduct.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        products_ = java.util.Collections.unmodifiableList(products_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return id.grocery.tunas.grpc.TransactionOuterClass.internal_static_proto_TransactionBody_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return id.grocery.tunas.grpc.TransactionOuterClass.internal_static_proto_TransactionBody_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            id.grocery.tunas.grpc.TransactionBody.class, id.grocery.tunas.grpc.TransactionBody.Builder.class);
  }

  private int bitField0_;
  public static final int USER_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object userId_;
  /**
   * <code>string user_id = 1;</code>
   */
  public java.lang.String getUserId() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userId_ = s;
      return s;
    }
  }
  /**
   * <code>string user_id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUserIdBytes() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRODUCTS_FIELD_NUMBER = 2;
  private java.util.List<id.grocery.tunas.grpc.TransactionProduct> products_;
  /**
   * <code>repeated .proto.TransactionProduct products = 2;</code>
   */
  public java.util.List<id.grocery.tunas.grpc.TransactionProduct> getProductsList() {
    return products_;
  }
  /**
   * <code>repeated .proto.TransactionProduct products = 2;</code>
   */
  public java.util.List<? extends id.grocery.tunas.grpc.TransactionProductOrBuilder> 
      getProductsOrBuilderList() {
    return products_;
  }
  /**
   * <code>repeated .proto.TransactionProduct products = 2;</code>
   */
  public int getProductsCount() {
    return products_.size();
  }
  /**
   * <code>repeated .proto.TransactionProduct products = 2;</code>
   */
  public id.grocery.tunas.grpc.TransactionProduct getProducts(int index) {
    return products_.get(index);
  }
  /**
   * <code>repeated .proto.TransactionProduct products = 2;</code>
   */
  public id.grocery.tunas.grpc.TransactionProductOrBuilder getProductsOrBuilder(
      int index) {
    return products_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getUserIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userId_);
    }
    for (int i = 0; i < products_.size(); i++) {
      output.writeMessage(2, products_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUserIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userId_);
    }
    for (int i = 0; i < products_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, products_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof id.grocery.tunas.grpc.TransactionBody)) {
      return super.equals(obj);
    }
    id.grocery.tunas.grpc.TransactionBody other = (id.grocery.tunas.grpc.TransactionBody) obj;

    boolean result = true;
    result = result && getUserId()
        .equals(other.getUserId());
    result = result && getProductsList()
        .equals(other.getProductsList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getUserId().hashCode();
    if (getProductsCount() > 0) {
      hash = (37 * hash) + PRODUCTS_FIELD_NUMBER;
      hash = (53 * hash) + getProductsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static id.grocery.tunas.grpc.TransactionBody parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(id.grocery.tunas.grpc.TransactionBody prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code proto.TransactionBody}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proto.TransactionBody)
      id.grocery.tunas.grpc.TransactionBodyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return id.grocery.tunas.grpc.TransactionOuterClass.internal_static_proto_TransactionBody_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return id.grocery.tunas.grpc.TransactionOuterClass.internal_static_proto_TransactionBody_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              id.grocery.tunas.grpc.TransactionBody.class, id.grocery.tunas.grpc.TransactionBody.Builder.class);
    }

    // Construct using id.grocery.tunas.grpc.TransactionBody.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getProductsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      userId_ = "";

      if (productsBuilder_ == null) {
        products_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        productsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return id.grocery.tunas.grpc.TransactionOuterClass.internal_static_proto_TransactionBody_descriptor;
    }

    public id.grocery.tunas.grpc.TransactionBody getDefaultInstanceForType() {
      return id.grocery.tunas.grpc.TransactionBody.getDefaultInstance();
    }

    public id.grocery.tunas.grpc.TransactionBody build() {
      id.grocery.tunas.grpc.TransactionBody result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public id.grocery.tunas.grpc.TransactionBody buildPartial() {
      id.grocery.tunas.grpc.TransactionBody result = new id.grocery.tunas.grpc.TransactionBody(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.userId_ = userId_;
      if (productsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          products_ = java.util.Collections.unmodifiableList(products_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.products_ = products_;
      } else {
        result.products_ = productsBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof id.grocery.tunas.grpc.TransactionBody) {
        return mergeFrom((id.grocery.tunas.grpc.TransactionBody)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(id.grocery.tunas.grpc.TransactionBody other) {
      if (other == id.grocery.tunas.grpc.TransactionBody.getDefaultInstance()) return this;
      if (!other.getUserId().isEmpty()) {
        userId_ = other.userId_;
        onChanged();
      }
      if (productsBuilder_ == null) {
        if (!other.products_.isEmpty()) {
          if (products_.isEmpty()) {
            products_ = other.products_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureProductsIsMutable();
            products_.addAll(other.products_);
          }
          onChanged();
        }
      } else {
        if (!other.products_.isEmpty()) {
          if (productsBuilder_.isEmpty()) {
            productsBuilder_.dispose();
            productsBuilder_ = null;
            products_ = other.products_;
            bitField0_ = (bitField0_ & ~0x00000002);
            productsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getProductsFieldBuilder() : null;
          } else {
            productsBuilder_.addAllMessages(other.products_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      id.grocery.tunas.grpc.TransactionBody parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (id.grocery.tunas.grpc.TransactionBody) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object userId_ = "";
    /**
     * <code>string user_id = 1;</code>
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string user_id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string user_id = 1;</code>
     */
    public Builder setUserId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 1;</code>
     */
    public Builder clearUserId() {
      
      userId_ = getDefaultInstance().getUserId();
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 1;</code>
     */
    public Builder setUserIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      userId_ = value;
      onChanged();
      return this;
    }

    private java.util.List<id.grocery.tunas.grpc.TransactionProduct> products_ =
      java.util.Collections.emptyList();
    private void ensureProductsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        products_ = new java.util.ArrayList<id.grocery.tunas.grpc.TransactionProduct>(products_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        id.grocery.tunas.grpc.TransactionProduct, id.grocery.tunas.grpc.TransactionProduct.Builder, id.grocery.tunas.grpc.TransactionProductOrBuilder> productsBuilder_;

    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public java.util.List<id.grocery.tunas.grpc.TransactionProduct> getProductsList() {
      if (productsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(products_);
      } else {
        return productsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public int getProductsCount() {
      if (productsBuilder_ == null) {
        return products_.size();
      } else {
        return productsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public id.grocery.tunas.grpc.TransactionProduct getProducts(int index) {
      if (productsBuilder_ == null) {
        return products_.get(index);
      } else {
        return productsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder setProducts(
        int index, id.grocery.tunas.grpc.TransactionProduct value) {
      if (productsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProductsIsMutable();
        products_.set(index, value);
        onChanged();
      } else {
        productsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder setProducts(
        int index, id.grocery.tunas.grpc.TransactionProduct.Builder builderForValue) {
      if (productsBuilder_ == null) {
        ensureProductsIsMutable();
        products_.set(index, builderForValue.build());
        onChanged();
      } else {
        productsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder addProducts(id.grocery.tunas.grpc.TransactionProduct value) {
      if (productsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProductsIsMutable();
        products_.add(value);
        onChanged();
      } else {
        productsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder addProducts(
        int index, id.grocery.tunas.grpc.TransactionProduct value) {
      if (productsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureProductsIsMutable();
        products_.add(index, value);
        onChanged();
      } else {
        productsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder addProducts(
        id.grocery.tunas.grpc.TransactionProduct.Builder builderForValue) {
      if (productsBuilder_ == null) {
        ensureProductsIsMutable();
        products_.add(builderForValue.build());
        onChanged();
      } else {
        productsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder addProducts(
        int index, id.grocery.tunas.grpc.TransactionProduct.Builder builderForValue) {
      if (productsBuilder_ == null) {
        ensureProductsIsMutable();
        products_.add(index, builderForValue.build());
        onChanged();
      } else {
        productsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder addAllProducts(
        java.lang.Iterable<? extends id.grocery.tunas.grpc.TransactionProduct> values) {
      if (productsBuilder_ == null) {
        ensureProductsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, products_);
        onChanged();
      } else {
        productsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder clearProducts() {
      if (productsBuilder_ == null) {
        products_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        productsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public Builder removeProducts(int index) {
      if (productsBuilder_ == null) {
        ensureProductsIsMutable();
        products_.remove(index);
        onChanged();
      } else {
        productsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public id.grocery.tunas.grpc.TransactionProduct.Builder getProductsBuilder(
        int index) {
      return getProductsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public id.grocery.tunas.grpc.TransactionProductOrBuilder getProductsOrBuilder(
        int index) {
      if (productsBuilder_ == null) {
        return products_.get(index);  } else {
        return productsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public java.util.List<? extends id.grocery.tunas.grpc.TransactionProductOrBuilder> 
         getProductsOrBuilderList() {
      if (productsBuilder_ != null) {
        return productsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(products_);
      }
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public id.grocery.tunas.grpc.TransactionProduct.Builder addProductsBuilder() {
      return getProductsFieldBuilder().addBuilder(
          id.grocery.tunas.grpc.TransactionProduct.getDefaultInstance());
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public id.grocery.tunas.grpc.TransactionProduct.Builder addProductsBuilder(
        int index) {
      return getProductsFieldBuilder().addBuilder(
          index, id.grocery.tunas.grpc.TransactionProduct.getDefaultInstance());
    }
    /**
     * <code>repeated .proto.TransactionProduct products = 2;</code>
     */
    public java.util.List<id.grocery.tunas.grpc.TransactionProduct.Builder> 
         getProductsBuilderList() {
      return getProductsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        id.grocery.tunas.grpc.TransactionProduct, id.grocery.tunas.grpc.TransactionProduct.Builder, id.grocery.tunas.grpc.TransactionProductOrBuilder> 
        getProductsFieldBuilder() {
      if (productsBuilder_ == null) {
        productsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            id.grocery.tunas.grpc.TransactionProduct, id.grocery.tunas.grpc.TransactionProduct.Builder, id.grocery.tunas.grpc.TransactionProductOrBuilder>(
                products_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        products_ = null;
      }
      return productsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:proto.TransactionBody)
  }

  // @@protoc_insertion_point(class_scope:proto.TransactionBody)
  private static final id.grocery.tunas.grpc.TransactionBody DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new id.grocery.tunas.grpc.TransactionBody();
  }

  public static id.grocery.tunas.grpc.TransactionBody getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransactionBody>
      PARSER = new com.google.protobuf.AbstractParser<TransactionBody>() {
    public TransactionBody parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransactionBody(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransactionBody> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransactionBody> getParserForType() {
    return PARSER;
  }

  public id.grocery.tunas.grpc.TransactionBody getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
