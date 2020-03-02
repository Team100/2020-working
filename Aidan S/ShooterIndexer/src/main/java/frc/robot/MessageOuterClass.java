package frc.robot;

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

public final class MessageOuterClass {
    private MessageOuterClass() {}
    public static void registerAllExtensions(
        com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
        com.google.protobuf.ExtensionRegistry registry) {
      registerAllExtensions(
          (com.google.protobuf.ExtensionRegistryLite) registry);
    }
    public interface MessageOrBuilder extends
        // @@protoc_insertion_point(interface_extends:Message)
        com.google.protobuf.MessageOrBuilder {

      /**
       * <code>double distance = 1;</code>
       * @return The distance.
       */
      double getDistance();

      /**
       * <code>double v_angle = 2;</code>
       * @return The vAngle.
       */
      double getVAngle();

      /**
       * <code>double h_angle = 3;</code>
       * @return The hAngle.
       */
      double getHAngle();
    }
    /**
     * Protobuf type {@code Message}
     */
    public  static final class Message extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:Message)
        MessageOrBuilder {
    private static final long serialVersionUID = 0L;
      // Use Message.newBuilder() to construct.
      private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
      }
      private Message() {
      }

      @java.lang.Override
      @SuppressWarnings({"unused"})
      protected java.lang.Object newInstance(
          UnusedPrivateParameter unused) {
        return new Message();
      }

      @java.lang.Override
      public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
        return this.unknownFields;
      }
      private Message(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder();
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 9: {

                distance_ = input.readDouble();
                break;
              }
              case 17: {

                vAngle_ = input.readDouble();
                break;
              }
              case 25: {

                hAngle_ = input.readDouble();
                break;
              }
              default: {
                if (!parseUnknownField(
                    input, unknownFields, extensionRegistry, tag)) {
                  done = true;
                }
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
          this.unknownFields = unknownFields.build();
          makeExtensionsImmutable();
        }
      }
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return MessageOuterClass.internal_static_Message_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return MessageOuterClass.internal_static_Message_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                MessageOuterClass.Message.class, MessageOuterClass.Message.Builder.class);
      }

      public static final int DISTANCE_FIELD_NUMBER = 1;
      private double distance_;
      /**
       * <code>double distance = 1;</code>
       * @return The distance.
       */
      public double getDistance() {
        return distance_;
      }

      public static final int V_ANGLE_FIELD_NUMBER = 2;
      private double vAngle_;
      /**
       * <code>double v_angle = 2;</code>
       * @return The vAngle.
       */
      public double getVAngle() {
        return vAngle_;
      }

      public static final int H_ANGLE_FIELD_NUMBER = 3;
      private double hAngle_;
      /**
       * <code>double h_angle = 3;</code>
       * @return The hAngle.
       */
      public double getHAngle() {
        return hAngle_;
      }

      private byte memoizedIsInitialized = -1;
      @java.lang.Override
      public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) return true;
        if (isInitialized == 0) return false;

        memoizedIsInitialized = 1;
        return true;
      }

      @java.lang.Override
      public void writeTo(com.google.protobuf.CodedOutputStream output)
                          throws java.io.IOException {
        if (distance_ != 0D) {
          output.writeDouble(1, distance_);
        }
        if (vAngle_ != 0D) {
          output.writeDouble(2, vAngle_);
        }
        if (hAngle_ != 0D) {
          output.writeDouble(3, hAngle_);
        }
        unknownFields.writeTo(output);
      }

      @java.lang.Override
      public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) return size;

        size = 0;
        if (distance_ != 0D) {
          size += com.google.protobuf.CodedOutputStream
            .computeDoubleSize(1, distance_);
        }
        if (vAngle_ != 0D) {
          size += com.google.protobuf.CodedOutputStream
            .computeDoubleSize(2, vAngle_);
        }
        if (hAngle_ != 0D) {
          size += com.google.protobuf.CodedOutputStream
            .computeDoubleSize(3, hAngle_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
      }

      @java.lang.Override
      public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
         return true;
        }
        if (!(obj instanceof MessageOuterClass.Message)) {
          return super.equals(obj);
        }
        MessageOuterClass.Message other = (MessageOuterClass.Message) obj;

        if (java.lang.Double.doubleToLongBits(getDistance())
            != java.lang.Double.doubleToLongBits(
                other.getDistance())) return false;
        if (java.lang.Double.doubleToLongBits(getVAngle())
            != java.lang.Double.doubleToLongBits(
                other.getVAngle())) return false;
        if (java.lang.Double.doubleToLongBits(getHAngle())
            != java.lang.Double.doubleToLongBits(
                other.getHAngle())) return false;
        if (!unknownFields.equals(other.unknownFields)) return false;
        return true;
      }

      @java.lang.Override
      public int hashCode() {
        if (memoizedHashCode != 0) {
          return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            java.lang.Double.doubleToLongBits(getDistance()));
        hash = (37 * hash) + V_ANGLE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            java.lang.Double.doubleToLongBits(getVAngle()));
        hash = (37 * hash) + H_ANGLE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            java.lang.Double.doubleToLongBits(getHAngle()));
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
      }

      public static MessageOuterClass.Message parseFrom(
          java.nio.ByteBuffer data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
      }
      public static MessageOuterClass.Message parseFrom(
          java.nio.ByteBuffer data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
      }
      public static MessageOuterClass.Message parseFrom(
          com.google.protobuf.ByteString data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
      }
      public static MessageOuterClass.Message parseFrom(
          com.google.protobuf.ByteString data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
      }
      public static MessageOuterClass.Message parseFrom(byte[] data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
      }
      public static MessageOuterClass.Message parseFrom(
          byte[] data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
      }
      public static MessageOuterClass.Message parseFrom(java.io.InputStream input)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
      }
      public static MessageOuterClass.Message parseFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
      }
      public static MessageOuterClass.Message parseDelimitedFrom(java.io.InputStream input)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input);
      }
      public static MessageOuterClass.Message parseDelimitedFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      public static MessageOuterClass.Message parseFrom(
          com.google.protobuf.CodedInputStream input)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
      }
      public static MessageOuterClass.Message parseFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
      }

      @java.lang.Override
      public Builder newBuilderForType() { return newBuilder(); }
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      public static Builder newBuilder(MessageOuterClass.Message prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      @java.lang.Override
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
       * Protobuf type {@code Message}
       */
      public static final class Builder extends
          com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
          // @@protoc_insertion_point(builder_implements:Message)
          MessageOuterClass.MessageOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
          return MessageOuterClass.internal_static_Message_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
          return MessageOuterClass.internal_static_Message_fieldAccessorTable
              .ensureFieldAccessorsInitialized(
                  MessageOuterClass.Message.class, MessageOuterClass.Message.Builder.class);
        }

        // Construct using MessageOuterClass.Message.newBuilder()
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
          }
        }
        @java.lang.Override
        public Builder clear() {
          super.clear();
          distance_ = 0D;

          vAngle_ = 0D;

          hAngle_ = 0D;

          return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
          return MessageOuterClass.internal_static_Message_descriptor;
        }

        @java.lang.Override
        public MessageOuterClass.Message getDefaultInstanceForType() {
          return MessageOuterClass.Message.getDefaultInstance();
        }

        @java.lang.Override
        public MessageOuterClass.Message build() {
          MessageOuterClass.Message result = buildPartial();
          if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
          }
          return result;
        }

        @java.lang.Override
        public MessageOuterClass.Message buildPartial() {
          MessageOuterClass.Message result = new MessageOuterClass.Message(this);
          result.distance_ = distance_;
          result.vAngle_ = vAngle_;
          result.hAngle_ = hAngle_;
          onBuilt();
          return result;
        }

        @java.lang.Override
        public Builder clone() {
          return super.clone();
        }
        @java.lang.Override
        public Builder setField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            java.lang.Object value) {
          return super.setField(field, value);
        }
        @java.lang.Override
        public Builder clearField(
            com.google.protobuf.Descriptors.FieldDescriptor field) {
          return super.clearField(field);
        }
        @java.lang.Override
        public Builder clearOneof(
            com.google.protobuf.Descriptors.OneofDescriptor oneof) {
          return super.clearOneof(oneof);
        }
        @java.lang.Override
        public Builder setRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            int index, java.lang.Object value) {
          return super.setRepeatedField(field, index, value);
        }
        @java.lang.Override
        public Builder addRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            java.lang.Object value) {
          return super.addRepeatedField(field, value);
        }
        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
          if (other instanceof MessageOuterClass.Message) {
            return mergeFrom((MessageOuterClass.Message)other);
          } else {
            super.mergeFrom(other);
            return this;
          }
        }

        public Builder mergeFrom(MessageOuterClass.Message other) {
          if (other == MessageOuterClass.Message.getDefaultInstance()) return this;
          if (other.getDistance() != 0D) {
            setDistance(other.getDistance());
          }
          if (other.getVAngle() != 0D) {
            setVAngle(other.getVAngle());
          }
          if (other.getHAngle() != 0D) {
            setHAngle(other.getHAngle());
          }
          this.mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
          return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          MessageOuterClass.Message parsedMessage = null;
          try {
            parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            parsedMessage = (MessageOuterClass.Message) e.getUnfinishedMessage();
            throw e.unwrapIOException();
          } finally {
            if (parsedMessage != null) {
              mergeFrom(parsedMessage);
            }
          }
          return this;
        }

        private double distance_ ;
        /**
         * <code>double distance = 1;</code>
         * @return The distance.
         */
        public double getDistance() {
          return distance_;
        }
        /**
         * <code>double distance = 1;</code>
         * @param value The distance to set.
         * @return This builder for chaining.
         */
        public Builder setDistance(double value) {

          distance_ = value;
          onChanged();
          return this;
        }
        /**
         * <code>double distance = 1;</code>
         * @return This builder for chaining.
         */
        public Builder clearDistance() {

          distance_ = 0D;
          onChanged();
          return this;
        }

        private double vAngle_ ;
        /**
         * <code>double v_angle = 2;</code>
         * @return The vAngle.
         */
        public double getVAngle() {
          return vAngle_;
        }
        /**
         * <code>double v_angle = 2;</code>
         * @param value The vAngle to set.
         * @return This builder for chaining.
         */
        public Builder setVAngle(double value) {

          vAngle_ = value;
          onChanged();
          return this;
        }
        /**
         * <code>double v_angle = 2;</code>
         * @return This builder for chaining.
         */
        public Builder clearVAngle() {

          vAngle_ = 0D;
          onChanged();
          return this;
        }

        private double hAngle_ ;
        /**
         * <code>double h_angle = 3;</code>
         * @return The hAngle.
         */
        public double getHAngle() {
          return hAngle_;
        }
        /**
         * <code>double h_angle = 3;</code>
         * @param value The hAngle to set.
         * @return This builder for chaining.
         */
        public Builder setHAngle(double value) {

          hAngle_ = value;
          onChanged();
          return this;
        }
        /**
         * <code>double h_angle = 3;</code>
         * @return This builder for chaining.
         */
        public Builder clearHAngle() {

          hAngle_ = 0D;
          onChanged();
          return this;
        }
        @java.lang.Override
        public final Builder setUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
          return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
          return super.mergeUnknownFields(unknownFields);
        }


        // @@protoc_insertion_point(builder_scope:Message)
      }

      // @@protoc_insertion_point(class_scope:Message)
      private static final MessageOuterClass.Message DEFAULT_INSTANCE;
      static {
        DEFAULT_INSTANCE = new MessageOuterClass.Message();
      }

      public static MessageOuterClass.Message getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }

      private static final com.google.protobuf.Parser<Message>
          PARSER = new com.google.protobuf.AbstractParser<Message>() {
        @java.lang.Override
        public Message parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return new Message(input, extensionRegistry);
        }
      };

      public static com.google.protobuf.Parser<Message> parser() {
        return PARSER;
      }

      @java.lang.Override
      public com.google.protobuf.Parser<Message> getParserForType() {
        return PARSER;
      }

      @java.lang.Override
      public MessageOuterClass.Message getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
      internal_static_Message_descriptor;
    private static final
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_Message_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
        getDescriptor() {
      return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
        descriptor;
    static {
      java.lang.String[] descriptorData = {
        "\n\rmessage.proto\"=\n\007Message\022\020\n\010distance\030\001" +
        " \001(\001\022\017\n\007v_angle\030\002 \001(\001\022\017\n\007h_angle\030\003 \001(\001b\006" +
        "proto3"
      };
      descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
          new com.google.protobuf.Descriptors.FileDescriptor[] {
          });
      internal_static_Message_descriptor =
        getDescriptor().getMessageTypes().get(0);
      internal_static_Message_fieldAccessorTable = new
        com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
          internal_static_Message_descriptor,
          new java.lang.String[] { "Distance", "VAngle", "HAngle", });
    }

    // @@protoc_insertion_point(outer_class_scope)
  }