#include "hal/proto/MatchInfo.h"

#include "wpi/protobuf/ProtobufCallbacks.h"

#include <string>
#include <utility>

std::optional<mrc::MatchInfo> wpi::Protobuf<mrc::MatchInfo>::Unpack(
    InputStream& Stream) {
    wpi::UnpackCallback<std::string> NameCb;

    mrc_proto_ProtobufMatchInfo Msg;
    Msg.EventName = NameCb.Callback();

    if (!Stream.Decode(Msg)) {
        return {};
    }

    auto Name = NameCb.Items();

    if (Name.empty()) {
        return {};
    }

    mrc::MatchInfo OutputData;
    OutputData.MoveEventName(std::move(Name[0]));

    OutputData.MatchNumber = Msg.MatchNumber;
    OutputData.ReplayNumber = Msg.ReplayNumber;
    OutputData.Type = static_cast<mrc::MatchType>(Msg.MatchType);

    return OutputData;
}

bool wpi::Protobuf<mrc::MatchInfo>::Pack(OutputStream& Stream,
                                        const mrc::MatchInfo& Value) {
    std::string_view EventNameStr = Value.GetEventName();
    wpi::PackCallback EventName{&EventNameStr};

    mrc_proto_ProtobufMatchInfo Msg{
        .EventName = EventName.Callback(),
        .MatchNumber = Value.MatchNumber,
        .ReplayNumber = Value.ReplayNumber,
        .MatchType = static_cast<int32_t>(Value.Type),
    };

    return Stream.Encode(Msg);
}
