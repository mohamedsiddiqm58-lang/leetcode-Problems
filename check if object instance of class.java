var checkIfInstanceOf = function(obj, classFunction) {

    if (obj === null || obj === undefined)
        return false;

    if (typeof classFunction !== "function")
        return false;

    let prototype = Object.getPrototypeOf(obj);

    while (prototype !== null) {

        if (prototype === classFunction.prototype)
            return true;

        prototype = Object.getPrototypeOf(prototype);
    }

    return false;
};
