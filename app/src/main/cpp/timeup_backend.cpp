#include <jni.h>
#include <pthread.h>
#include <unistd.h>



int main() {
    while (true) {
        system("echo 'test' > /data/user/0/com.system.timeup/files/test");
        sleep(1);
    }
    
    return 0;
}

static void* backend_thread(void*) {
    main();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_system_timeup_NativeBackend_start(JNIEnv*, jclass) {
    pthread_t tid;
    pthread_create(&tid, nullptr, backend_thread, nullptr);
    pthread_detach(tid);    // 非阻塞
}