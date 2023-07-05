package de.kenjih.subapi.objects.interfaces;

import de.kenjih.subapi.objects.enums.Response;

public interface AsynchroneCallback {
    void onComplete(Response response);
}
