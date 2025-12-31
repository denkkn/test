LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE            := timeup_backend
LOCAL_SRC_FILES         := timeup_backend.cpp
include $(BUILD_SHARED_LIBRARY)
