// IAddService.aidl
package spdatabase.spinc.servicethreadhandler.aidl;

interface IAddService {
    // WE can pass values along with in, out, or inout parameters.
    // Android Java Primitive datatypes (such as int,, string, boolean, etc.) can only be passed in.
    int add(in int ValueFirst, in int valueSecond);
}
