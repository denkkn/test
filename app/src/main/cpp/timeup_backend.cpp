#include <jni.h>
#include <pthread.h>
#include <unistd.h>

static void* backend_thread(void*) {
    while (true) {
        // 你的后端任务
        sleep(60);          // 每 60 s 干一次活
    }
    return nullptr;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_system_timeup_NativeBackend_start(JNIEnv*, jclass) {
    pthread_t tid;
    pthread_create(&tid, nullptr, backend_thread, nullptr);
    pthread_detach(tid);    // 非阻塞
}