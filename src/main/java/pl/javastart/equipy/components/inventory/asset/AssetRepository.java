package pl.javastart.equipy.components.inventory.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query(value = "select * from Asset a where lower(a.name) like lower(concat('%', :search, '%')) " +
            "or lower(a.serial_number) like lower(concat('%', :search, '%')) ", nativeQuery = true)
    List<Asset> findAssetsByNameOrSerialNumber(@Param("search") String search);

}
