// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wishlist.proto

package id.grocery.tunas.grpc;

public interface WishlistResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:proto.WishlistResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string status = 1;</code>
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 1;</code>
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>.proto.WishlistDetail data = 3;</code>
   */
  boolean hasData();
  /**
   * <code>.proto.WishlistDetail data = 3;</code>
   */
  id.grocery.tunas.grpc.WishlistDetail getData();
  /**
   * <code>.proto.WishlistDetail data = 3;</code>
   */
  id.grocery.tunas.grpc.WishlistDetailOrBuilder getDataOrBuilder();
}