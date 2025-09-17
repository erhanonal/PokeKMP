import ComposeApp

func get<A: AnyObject>() -> A {
    return KoinHelper.companion.koin.get(objCClass: A.self) as! A
}

func get<A: AnyObject>(_ type: A.Type) -> A {
    return KoinHelper.companion.koin.get(objCClass: A.self) as! A
}

func get<A: AnyObject>(_ type: A.Type, qualifier: Koin_coreQualifier? = nil, parameter: Any) -> A {
    return KoinHelper.companion.koin.get(objCClass: A.self, qualifier: qualifier, parameter: parameter) as! A
}